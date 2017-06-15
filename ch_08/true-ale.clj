(ns true-ale.core)

(def order-details
  {:name "Andrew Jarrett"
   :email "ahrjarrett@gmail.com"})




;; RE-SEQ (http://clojuredocs.org/clojure.core/re-seq):
;; Returns a lazy sequence of successive matches of pattern in string,
;; using java.util.regex.Matcher.find(), each such match processed with
;; re-groups.

(def order-details-verifications
  {:name ["Please enter a name" not-empty]
   :email
   ["Please enter an email address" not-empty
    "Your email address doesn't look like an email address"
    #(or (empty? %) (re-seq #"@" %))]})


(validate order-details order-details-validations)
;; {:email ["Your email address doesn't look like an email address."]}
