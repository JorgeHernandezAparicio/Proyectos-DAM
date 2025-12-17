# Sistema de Login y Registro

Este proyecto gestiona el acceso y alta de usuarios mediante el manejo de ficheros de texto.

##Login
1. **Pedir username y password**
    * 1ª- Si **username** está vacío -> **Fin** (Si al pedir el user no pone nada).
    * 2ª- Si la **password** está vacía -> **Fin** (Si al pedir la password no pone nada).
2. **Abrir user.txt** y guardar líneas en array.
3. **Recorrer línea a línea** y separar los campos (**split**).
    * 3ª- Comprobar si **username == Campos[0]** y **password == Campos[1]**.

##Registro
1. **Pedir Username (equals.ignorecase)**
    * Procesar **user.txt**, recorrer líneas y comprobar si está el username.
    * Si está -> (**FIN**).
2. **Pedir Password 2 veces**
    * Si no son iguales estrictamente (**FIN**).
3. **Pedir el resto de los campos**.
4. **Construir la nueva línea** (y al final añadir el rol user).
5. **Escribir la línea en user.txt (FileWriter)** -> coger de w3schools.
6. **Mensaje registro OK** -> **FIN**.

---
*Proyecto de DAM - Gestión de usuarios con persistencia en ficheros.*
