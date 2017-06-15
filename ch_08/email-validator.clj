(def order-details
  {:name "Andrew Jarrett"
   :email "ahrjarrett@gmail.com"})

;; RE-SEQ (http://clojuredocs.org/clojure.core/re-seq):
;; Returns a lazy sequence of successive matches of pattern in string,
;; using java.util.regex.Matcher.find(), each such match processed with
;; re-groups.

(def order-details-validations
  {:name ["Please enter a name" not-empty]
   :email
   ["Please enter an email address" not-empty
    "Your email address doesn't look like an email address"
    #(or (empty? %) (re-seq #"@" %))]})

(defn error-messages-for [to-validate message-validator-pairs]
  (map first (filter #(not ((second %) to-validate))
                     (partition 2 message-validator-pairs))))

;(error-messages-for "" ["Please enter a name" not-empty])

;; VALIDATE uses ASSOC:
;; (assoc map key val & kvs)
;; assoc[iate]. When applied to a map, returns a new map of the
;; same (hashed/sorted) type, that contains the mapping of key(s) to
;; val(s). When applied to a vector, returns a new vector that
;; contains val at index. Note - index must be <= (count vector).

;; http://clojuredocs.org/clojure.core/assoc

(defn validate
  [to-validate validations]
  (reduce (fn [errors validation]
            (let [[fieldname validation-check-groups] validation
                  value (get to-validate fieldname)
                  error-messages (error-messages-for value validation-check-groups)]
              (if (empty? error-messages)
                errors
                (assoc errors fieldname error-messages))))
          {}
          validations))

(validate order-details order-details-validations)
;; => {:email ("Your email address doesn't look like an email address.")}

;; PATTERN for error handling in validations:
(let [errors (validate order-details order-details-validations)]
  (if (empty? errors)
    (println :success)
    (println :failure errors)))
;; => :failure {:email (Your email address doesn't look like an email address)}
;; => :success

;; IF-VALID macro, which abstracts the pattern above:
(defmacro if-valid
  [to-validate validations errors-name & then-else]
  `(let [~errors-name (validate ~to-validate ~validations)]
     (if (empty? ~errors-name)
       ~@then-else)))

(macroexpand
 '(if-valid order-details order-details-validations my-error-name
            (println :success)
            (println :failure my-error-name)))

