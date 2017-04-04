(ns fwpd.core)
(def filename "suspects.csv")
(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))

(def conversions {:name identity :glitter-index str->int})

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))
  ; (convert :glitter-index "5") ; => 5

(defn my-parse
  "Convert a CSV into rows of columns"
  [string]
  (map (#(clojure.string/split % #","))
       (clojure.string/split string #"\n")))

; BOOKMARK: Page 95, can't get (parse (slurp filename)) to work




