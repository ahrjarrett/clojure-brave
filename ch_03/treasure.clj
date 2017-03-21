(defn announce-treasure-location
  [{:keys [lng lat]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))

(defn receive-treasure-location
  [{:keys [lat lng]} :as treasure-location}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng))
  ; obv steer-ship doesn’t exist
  (steer-ship! treasure-location))
