;; Anything you type in here will be executed
;; immediately with the results shown on the
;; right.

(> 1 -22 -30)

(+)

(*)

(/ 1)

(- 1)

(type (+ 1 2.0 3))

(min "a" "b")

(zero? 2)

(> "a" "b")


(map every? (map > [4 3 2 1]))

(> 1)
(< 1)
(= 1)

(every? > [4 3 2 1])
(every? > [4 3 1 2])


(apply > [4 3 1 2])
(apply > [4 3 2 1])
(apply > [4 3 2 1])
(apply > (list 4 3 1 2))
(apply > (list 4 3 2 1))
(apply max [4 67 1 3])
(reduce max [4 67 1 3])
(reduce > (list 4 3 2 1))

(first [])

(rest [])

(rest nil)

(seq [])

(seq nil)

(type (conj nil 1 2))

(first nil)

(count nil)

(= (seq [1 3]) (keys (into {} [[1 2 3] [3 4]])))
(= (seq [1 3]) (keys (into {} [[1 "a"] [3 "b"]])))
(into [] {1 2 3 4})
(into () '(1 2 3))
(into [] '(1 2 3))

(map + [1 2 3] [4 5 6] [7 8 9])

(type(first (seq {1 2 3 4})))

(> '(1 2 3))
(apply > '(1 2 3))

(apply list [1 2 3])

(reduce conj [] '(1 2 3 4) )

(into {} '([1 2] [3 4] [5 6]))

(conj {} [1 2] [3 4] [5 6])

(conj {} nil)

(type (conj (seq [1 2 3]) 4))

(conj {} [1 2])

(> '(3 2 1) '(3 2 1))

(hash-map 1 2)

(hash 1)

(.hashCode [1 2])

(.hashCode (first {1,2}))

(get '(1 2 3 4 5 6) 1)

(apply vector (range 5))

(apply map vector [[:a :b] [:c :d]])
(map vector (seq [[:a :b] [:c :d]]))

(apply hash-set [1 2 3 4])
(apply hash-set )


(rest [])

(cons nil (seq [1 2 3]))

(apply map vector [[:a :b] [:c :d]])

(map vector [:a :b] [:c :d])



(vector (seq [[:a :b] [:c :d]]))

(map vector (seq [[:a :b] [:c :d]]))

(map (seq vector [[:a :b] [:c :d]]))
(seq [:a :b] [:c :d])

(sequence 1 2 3 4 5)

(seq 1 2 3)

