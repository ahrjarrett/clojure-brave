;; Un-memoized:
(defn sleepy-identity
  [x]
  (Thread/sleep 1000)
  x)
(sleepy-identity "Captain Fantastic")


;; Memoized:
(def memo-sleepy-id (memoize sleepy-identity))
(memo-sleepy-id "Captain Fantastic")

