; Using map to create a new data structure,
; plucking out a single keyword and making a
; list of the corresponding value:
(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])

(map :real identities)
; => ("Bruce Wayne" "Peter Parker" "Your mom" "Your dad")
