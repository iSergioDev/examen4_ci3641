import sys

class Class:
    def __init__(self, name, methods: list, parent=None):
        self.name = name
        self.parent = parent
        self.finalMethods = {}

        # Si hay una clase base, heredamos sus métodos
        if isinstance(parent, Class):
            self.finalMethods.update(parent.finalMethods)

        # Añadimos los métodos propios de la clase
        for method in methods:
            self.finalMethods[method] = name

class Table:
    def __init__(self):
        self.table = {}

    def declare(self, action: list):
        className = action[0]

        # Verificar si la clase ya existe
        if className in self.table:
            return f"Error: La clase {className} ya está definida."

        # Verificar si la clase tiene una clase base
        if action[1] == ":":
            parentName = action[2]
            if parentName in self.table:
                parent = self.table[parentName]
                methods = action[3:]
            else:
                return f"Error: La clase base {parentName} no está definida."
        else:
            parent = None
            methods = action[1:]

        # Comprobamos si hay métodos duplicados
        if len(methods) != len(set(methods)):
            return "Error: Los métodos deben tener nombres diferentes."

        # Crear la clase y agregarla a la tabla
        new_class = Class(className, methods, parent)
        self.table[className] = new_class

        return f"La clase {className} ha sido definida."

    def describe(self, action: str):

        if action not in self.table:
            return f"Error: La clase {action} no está definida."

        class_type = self.table[action]
        methods = class_type.finalMethods

        description = []
        for method, class_name in sorted(methods.items()):
            description.append(f"{method} -> {class_name} :: {method}")

        return "\n".join(description)


# Programa principal
def main(action):
    action = action.strip().split()

    if not action:
        return "Error: Acción inválida."

    # Declarar clase
    if action[0] == "CLASS":
        return table.declare(action[1:])

    # Describir clase
    elif action[0] == "DESCRIBIR":
        if len(action) < 2:
            return "Error: Se necesita el nombre de la clase."
        return table.describe(action[1])

    # Salir
    elif action[0] == "SALIR":
        sys.exit()

    # Acción inválida
    return "Error: Acción no reconocida."

# Programa
table = Table()

# Punto de Entrada
if __name__ == "__main__":
    while True:
        print("\nIngrese una acción:")
        action = input().strip()
        result = main(action)
        print(result)
