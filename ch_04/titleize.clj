(defn titleize
  [topic]
  (str topic " for the Brave and True")
  )

;; I don't fully understand why SECOND works to access the value here,
;; while running SECOND on {:some-key "value"} returns NIL
(map #(titleize (second %)) {:silly-title "Winking" :other-title "Sleeping"})

