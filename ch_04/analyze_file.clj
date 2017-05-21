;; Analyze file
(def filename "suspects.csv")

(defn analysis [text]
  (str "Character count: " (count text)))

(defn analyze [filename]
  (analysis (slurp filename)))

(analyze filename)
