(defn lousy-logger
  [log-level msg]
  (condp = log-level
    :warn (clojure.string/lower-case msg)
    :emergency (clojure.string/upper-case msg)))

(def warn (partial lousy-logger :warn))

(warn "You put that roast on the table")

