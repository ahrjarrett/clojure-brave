
;; This is the actual implementation of WHEN:
(defmacro my-when
  [test & body]
  (list 'if test (cons 'do body)))

;; Same with UNLESS:
(defmacro my-unless
  [test & branches]
  (conj (reverse branches) test 'if))

;; So simple! There's that word again.

(macroexpand '(my-unless (done-been slapped? me)
                      (slap me :silly)
                      (say "I reckon that'll learn me")))

;; Syntax quoting will always include the symbol's full namespace:
`+ ;; => clojure.core/+

;; It also allows you to "unquote" with the tilde, allowing for evaluation:
`(+ 1 ~(inc 1)) ;; => (clojure.core/+ 1 2)

(defmacro infix
  [infixed]
  (list (second infixed) (first infixed) (last infixed)))

(infix (1 + 1))

(macroexpand '(infix (2 + 2))) ;; => (+ 2 2)

(defmacro destructured-infix [[operand1 operator operand2]]
  (list operator operand1 operand2))

(macroexpand '(destructured-infix (4 + 7))) ;; => (+ 4 7)

(destructured-infix (3 + 3)) ;; => 6

;; `SYNTAX QUOTE`
;; Syntax quotes return the fully qualified symbols:

`(+ 1 2 3) ;; => (clojure.core/+ 1 2 3)

;; ~TILDE~
;; Syntax quoting also allows you to unquote forms using the tilde:
`(+ 1 ~(inc 1))

(defmacro code-critic
  [bad good]
  (list 'do
        (list 'println
              "this is bad code:"
              (list 'quote bad))
        (list 'println
              "this is good code:"
              (list 'quote good))))

(code-critic (1 + 1) (+ 1 1))

(defmacro new-code-critic
  [bad good]
  `(do (println "This is bad code:"
                (quote ~bad))
       (println "This is good code:"
                (quote ~good))))

(new-code-critic (3 + 4) (+ 3 4))

(defn criticize-code [criticism code]
  `(println ~criticism (quote ~code)))

;; Writing it this way not only breaks the
;; duplicated work into its own fn, it also
;; removed the multiple calls to that function
;; with map:

;;(defmacro newer-code-critic [bad good]
;;  `(do ~(map #(apply criticize-code %)
;;       [["THIS IS BAD CODE!" bad]
;;        ["THIS IS GOOD CODE:" good]])))
;;
;;(newer-code-critic (5 * 7) (* 5 7))

;; But why are we getting a NullPointerException? We need unquote splicing:


;; ~@ UNQUOTE SPLICING

;; "Unquote splicing unwraps a seqable data structure, placing its contents directly within the enclosing syntax-quoted data structure."

`(+ (list 1 2 3)) ;; => (clojure.core/+ (clojure.core/list 1 2 3))

`(+ ~(list 1 2 3)) ;; => (clojure.core/+ (1 2 3))

`(+ ~@(list 1 2 3)) ;; => (clojure.core/+ 1 2 3)

;; Rewriting code critic:

;; Couldn't get this one to work? moving on, 06/15/17

;;(defmacro newest-code-critic
;;  [{:keys [good bad]}]
;;  `(do ~@(map #(apply criticize-code %)
;;              [["this really is bad code, you know:" bad]
;;               ["this is code to be proud of:" good]])))

;;(defmacro newest-code-critic
;;  [{:keys [good bad]}]
;;  `(do ~@(map #(apply criticize-code %)
;;              [["Sweet lion of Zion, this is bad code:" bad]
;;               ["Great cow of Moscow, this is good code:" good]])))

;;(newest-code-critic (1 + 1) (+ 1 1))


;; GENSYM creates a unique symbol for each successive call

(gensym 'uuid) ;; uuid10910


;; NEW SYNTAX: ending words with a # is shorthand for gensym.

`(uuid# uuid#) ;; (uuid__10913__auto uuid__10913__auto)

;; This is very helpful to avoid overwriting other variables
;; from within a let statement of a macro.
;; Also called avoiding "variable capture"


;; Double Evaluation:

(defmacro bad-report [to-try]
  `(if ~to-try
     (println (quote ~to-try) "was successful:" ~to-try)
     (println (quote ~to-try) "was not successful:" ~to-try)))

;; This ends up taking 2 seconds, instead of 1:
(bad-report (do (Thread/sleep 1000) (+ 1 1)))

;; We can fix this by assigning a variable to the procedure, so that
;; the do block is only invoked once:

(defmacro good-report [to-try]
  `(let [result# ~to-try]
     (if result#
       (println (quote ~to-try) "was successfuller:" result#)
       (println (quote ~to-try) "was unsuccessfuller:" result#))))

;(good-report (do (Thread/sleep 1000) (+ 1 1)))

(defmacro doseq-macro
  [macroname & args]
  `(do
     ~@(map (fn [arg] (list macroname arg)) args)))

(doseq-macro good-report (= 1 1) (= 1 2))

