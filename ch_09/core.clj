;; Chapter 9: Concurrent & Parallel Programming

;; FUTURES:
;; To get a future's result you have to DEREF or use the @ macro:
(let [result (future (println "this prints once")
                     (+ 1 1))]
  (println "deref: " (deref result))
  (println "@: " @result))

(let [another-result (future (Thread/sleep 500)
                             (+ 1 2))]
  @another-result) ;; => 3

;; Sometimes you want to place a time limit on how long to wait for a future.
;; To do that, you can pass deref a number of milliseconds to wait along with
;; the value to return if the deref times out:

(deref (future (Thread/sleep 300) 0) 10 5) ;; => 5

;; You can also check to see if the future is done running with REALIZED?

(realized? (future (Thread/sleep 300))) ;; false

(let [f (future)]
  @f
  (realized? f)) ;; true


;; DELAY allows you to define a task without having to execute it or
;; require the result immediately:

(def jackson-5-delay
  (delay (let [message "Just call my name and I'll be there."]
           (println "First deref:" message)
           message)))

;; FORCE evaluates the delay and dereferences it:

(force jackson-5-delay) ;; => "Just call my name and I'll be there."

;; Is the same as:

@jackson-5-delay

(def gimli-headshots ["serious.jpg" "fun.jpg" "playful.jpg"])
(defn email-user
  [email-address]
  (println "Sending headshot notification to " email-address))
(defn upload-document
  "Needs to be implemented"
  [headshot]
  true)
(let [notify (delay (email-user "and-my-axe@gmail.com"))]
  (doseq [headshot gimli-headshots]
    (future (upload-document headshot)
            (force notify))))

