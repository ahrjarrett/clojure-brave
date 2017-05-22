(defmacro backwards
  [form]
  (reverse form))

(backwards (" backwards" " am" "I" str)) ;; "I am backwards"

(def addition-list (list + 1 2 3))
(eval addition-list) ;; => 6
(eval (concat addition-list [10])) ;; => 16

(eval (list 'def 'lucky-number (concat addition-list [10])))
lucky-number ;; => 16

;; READ-STRING
(read-string "#(+ 1 %)") ;; => (fn* [p1__20853#] (+ 1 p1__20853#))
(read-string "'(a b d)") ;; => (quote (a b d))
(read-string "@new-var") ;; => (clojure.core/deref new-var)
(read-string "; ignore!\n(+ 1 2)") ;; => (+ 1 2)









