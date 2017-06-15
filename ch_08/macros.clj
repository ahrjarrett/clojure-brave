
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

;; Syntax quotes return the fully qualified symbols:

`(+ 1 2 3) ;; => (clojure.core/+ 1 2 3)










