from unittest import TestCase
from simulador import *

class TestSuit(TestCase):

    def test_main(self):

        self.assertEqual(main("CLASS A f g"), "La clase A ha sido definida.")
        self.assertEqual(main("DESCRIBIR A"), "f -> A :: f\ng -> A :: g")
        self.assertEqual(main("CLASS A f g"), "Error: La clase A ya está definida.")
        self.assertEqual(main("DESCRIBIR B"), "Error: La clase B no está definida.")
        self.assertEqual(main("CLASS B : A f h"), "La clase B ha sido definida.")
        self.assertEqual(main("CLASS C : Z f h"), "Error: La clase base Z no está definida.")
        self.assertEqual(main("CLASS E a n a"), "Error: Los métodos deben tener nombres diferentes.")