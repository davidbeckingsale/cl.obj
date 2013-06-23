(ns clobj.lib.input
  (:import (org.lwjgl.input Keyboard)))

(def keymap 
  {:left Keyboard/KEY_LEFT
   :right Keyboard/KEY_RIGHT
   :up Keyboard/KEY_UP
   :down Keyboard/KEY_DOWN
   :escape Keyboard/KEY_ESCAPE
   :w Keyboard/KEY_W
   :a Keyboard/KEY_A
   :s Keyboard/KEY_S
   :d Keyboard/KEY_D
   :space Keyboard/KEY_SPACE})

(defn key? [code]
  (get keymap code))
