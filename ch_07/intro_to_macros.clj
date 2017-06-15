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

;; using the "threading" or "stabby" macro:
(defn read-resource
  [path]
  (-> path
      clojure.java.io/resource
      slurp
      read-string))

;; Macro for infix instead of prefix notation:
(defmacro infix
  [infixed]
  (list (second infixed)
        (first infixed)
        (last infixed)))

(infix (1 + 2)) ;; => 3
;; For some reason this only works if I evaluate the entire buffer,
;; rather than the single function eval that I usually do.

