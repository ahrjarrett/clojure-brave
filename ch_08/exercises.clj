;; 1. Write the macro when-valid so that it behaves similarly to when.
;; Here is an example of calling it:

(defn error-messages-for
  "Return a seq of error messages"
  [to-validate message-validator-pairs]
  (map first (filter #(not ((second %) to-validate))
                     (partition 2 message-validator-pairs))))


(error-messages-for "" ["Please enter a name" not-empty])
; => ("Please enter a name")


(defn validate
  "Returns a map with a vector of errors for each key"
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
; => {:email ["Your email address doesn't look like an email address"]}


(let [errors (validate order-details order-details-validations)]
  (if (empty? errors)
    (println :success)
    (println :failure errors)))


(defn bad-if-valid
  [record validations success-code failure-code]
  (let [errors (validate record validations)]
    (if (empty? errors)
      success-code
      failure-code)))


(defmacro if-valid
  "Handle validation more concisely"
  [to-validate validations errors-name & then-else]
  `(let [~errors-name (validate ~to-validate ~validations)]
     (if (empty? ~errors-name)
       ~@then-else)))


(defmacro when-valid
  [to-validate validation & body]
  `(if (empty? (validate ~order-details ~order-details-validations))
     (do ~@body)
     nil))

(when-valid order-details order-details-validation
 (println "It's a success!")
 (println "There were no errors.")
 :success)



;; 2. You saw that and is implemented as a macro. Implement or as a macro.

;; OR: Evaluates exprs one at a time, from left to right. If a form
;; returns a logical true value, or returns that value and doesn't
;; evaluate any of the other expressions, otherwise it returns the
;; value of the last expression. (or) returns nil.
;; https://clojuredocs.org/clojure.core/or

(defmacro or-macro
  ([] nil)
  ([x] x)
  ([x & next]
   `(let [or# ~x]
      (if or#
        or#
        (or-macro ~@next)))))

;; Testing:

(or "stuff")
(or-macro "more stuff")

(or "sally")
(or-macro "salad")

(or false "sally")
(or-macro false "salty")

(or false false "sally")
(or-macro false false "salary")

;; Works! 06/15/17



;; EXERCISE 3:
;; 3. In Chapter 5 you created a series of functions
;; (c-int, c-str, c-dex) to read an RPG character’s
;; attributes. Write a macro that defines an arbitrary
;; number of attribute-retrieving functions using one macro call.
;; Here’s how you would call it:

;(defattrs c-int :intelligence
;  c-str :strength
;  c-dex :dexterity)





;; This is the solution provided (have not read yet 06/15/17):
(def character
  {:name "Smooches McCutes"
   :attributes {:intelligence 10
                :strength 4
                :dexterity 5}})

(defmacro defattrs
  ([] nil)
  ([fn-name attr]
   `(def ~fn-name (comp ~attr :attributes))
   )
  ([fn-name attr & rest]
   `(do
      (defattrs ~fn-name ~attr)
      (defattrs ~@rest))
   )
  )

(macroexpand `(defattrs c-int :intelligence
                c-str :strength
                c-dex :dexterity))
(defattrs c-int :intelligence
  c-str :strength
  c-dex :dexterity)

(c-str character)


(defattrs c-int :intelligence
  c-str :strength
  c-dex :dexterity)


























