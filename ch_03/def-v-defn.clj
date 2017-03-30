; Learning on my own: the difference between def and defn
(def t0 (System/currentTimeMillis))
(defn t1 [] (System/currentTimeMillis))
(println t0) ; => 1490853247313
(println (t1)) ; => 1490853247315
(println t0) ; => 1490853247313
(println (t1)) ; => 1490853247318
