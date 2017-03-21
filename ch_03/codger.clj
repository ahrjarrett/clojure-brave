(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn, " whippersnapper "!"))

(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))

; need to learn how to run tests
(codger ["Kanye" "Fiddy" "Kid Rock"])
