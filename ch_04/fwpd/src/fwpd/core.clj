;;(ns fwpd.core)
;;(def filename "suspects.csv")
;;(def vamp-keys [:name :glitter-index])
;;
;;(defn str->int
;;  [str]
;;  (Integer. str))
;;
;;(def conversions {:name identity :glitter-index str->int})
;;
;;(defn convert
;;  [vamp-key value]
;;  ((get conversions vamp-key) value))
;;  ; (convert :glitter-index "5") ; => 5
;;
;;(defn my-parse
;;  "Convert a CSV into rows of columns"
;;  [string]
;;  (map (#(clojure.string/split % #","))
;;       (clojure.string/split string #"\n")))

; BOOKMARK: Page 95, can't get (parse (slurp filename)) to work

;; Picked back up 05/21/17:
(ns fwpd.core)

(def filename "suspects.csv")

(slurp filename)

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))


(def conversions {:name identity
                  :glitter-index str->int})

(defn convert [vamp-key value]
  ((get conversions vamp-key) value))

(defn parse
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(defn mapify
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(:name (first (mapify (parse (slurp filename)))))

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))

(glitter-filter 3 (mapify (parse (slurp filename))))
;; => ({:name "Edward Cullen", :glitter-index 10} {:name "Jacob Black", :glitter-index 3} {:name "Carlisle Cullen", :glitter-index 6})


