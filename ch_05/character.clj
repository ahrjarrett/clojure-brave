(require '[clojure.string])

(def character
  {:name "Karl Marx"
   :attributes {:intelligence 10
                :strength 5
                :dexterity 8}})

(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))

(c-int character) ;; => 10
(c-str character) ;; => 5
(c-dex character) ;; => 8

;; Notice that the INT function rounds a number DOWN:
(defn old-spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))

(spell-slots character) ;; => 6


;; Now rewritten with COMP:
(def spell-slots
  (comp int inc #(/ % 2) c-int))

(spell-slots character)

;; This function composes two functions:
(defn compose-two
  [f g]
  (fn [& args]
    (f (apply g args))))

;; Why is this simply returning "q"?
;;(defn trimify
;;  [str]
;;  clojure.string/replace str #"s" "q")


;; -- EXERCISES --
;; Use compose-two:

(def your-favorite-song
  ["Don't" "you" "forget" "about" "me"])

(defn sing-it
  [lyrics]
  (into [] (str "Ready to sing? Here we go: " lyrics)))

(defn get-lyrics
  [lyrics]
  your-favorite-song)

(defn staccato-sing [song]
  (compose-two sing-it (get-lyrics song)))

(sing-it your-favorite-song)

;; STACCATO-SING doesn't work atm.
;;(staccato-sing your-favorite-song)

(def new-one (compose-two inc #(/ % 2)))
(new-one 3) ;; => 5/2

