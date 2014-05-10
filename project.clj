(defproject clojure4j "0.1.0-SNAPSHOT"
  :description "A partial Java binding to the Clojure programming language"
  :url "https://github.com/bluesxman/clojure4j"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.6.0"]]
  :profiles {:dev {:dependencies [[junit/junit "4.11"]]}}
  :plugins [[lein-junit "1.1.2"]]
  :source-paths ["src"]
  :java-source-paths ["src/java" "test/java"]
  :javac-options ["-target" "1.8" "-source" "1.8"]
  :compile-path "classes"
  :target-path "jar"
  :jar-name "clojure4j.jar"
  :junit ["test/java"]
  :aot [clojure4j.Core])
