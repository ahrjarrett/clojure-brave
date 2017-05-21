(defn unoptimized-sum
  ([vals] (sum vals 0))
  ([vals acc]
   (if (empty? vals) acc
       (sum (rest vals) (+ (first vals) acc)))))

;; Rewritten with RECUR to make sure we use tail recursion:
(defn sum
  ([vals] (sum vals 0))
  ([vals acc]
    (if (empty? vals) acc
        (recur (rest vals) (+ (first vals) acc)))))

(sum [4 3 4 6]) ;; => 17
(sum [4 3 4 6] 10) ;; => 27

