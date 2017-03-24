; “It’s as if LOOP creates an anonymous function
; with a parameter named ITERATION, and
; RECUR allows you to call the function from w/i
; itself, passing the argument (inc iteration).”
(loop [iteration 0]
  (println (str "Iteration #" iteration))
  (if (> iteration 3)
    (println "Adios!")
    (recur (inc iteration))))

; So the alternative clause is run if iteration is
; less than 3; and the RECUR function allows the
; loop to call itself again (almost like next()
; in Express, it seems?)

; Here’s how you could write the function above
; w/o using LOOP:
(defn recursive-printer
  ([]
    (recursive-printer 0))
  ([iteration]
    (println iteration)
    (if (> iteraction 3)
      (println "Goodbye!")
      (recursive-printer (inc iteration)))))
(recursive-printer)

; second example is very difficult to follow
