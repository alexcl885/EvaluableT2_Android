# Programación Multimedia y Dispositivos Móviles T_2
Autor : Alejandro Copado López

En esta actividad, he desarrollado un conjunto de funcionalidades variadas que he implementado a lo largo del tema 2. Estas incluyen múltiples `activities` y diferentes interacciones que enriquecen la experiencia del usuario.

---

## Activities Implementadas
1. **Login**
2. **Formulario**
3. **Main**
4. **Instrucciones**
5. **Llamada telefónica**
6. **Juego de Dados**
7. **Cuenta Chistes**

---

### 1. Login
El `Login` es un sistema de acceso básico pero seguro, que permite a los usuarios iniciar sesión en la aplicación. Si el usuario o la contraseña no son correctos, existe un botón que redirige mediante un `intent` explícito a otra `activity` donde se encuentra un formulario para registrarse. Al pasar a la `MainActivity`, se añade un `putExtra` para transferir el nombre del usuario.

---

### 2. Formulario
El `Formulario` se accede desde el botón del `Login`. Aquí, los usuarios deben completar los siguientes campos obligatorios:

- **TextField**: Para introducir un nombre de usuario.
- **PasswordField**: Para introducir una contraseña.
- **RadioButtons**: Para indicar el sexo (opciones masculino, femenino, otro).
- **Checkboxes**: Dos opciones para recoger más información del usuario.
- **Spinner**: Para seleccionar el uso que el usuario hará de la aplicación.
- **Botón de Enviar**: Que redirige mediante un `intent` a la `MainActivity`, transfiriendo el nombre de usuario con un `putExtra`.

---

### 3. Main
La `MainActivity` es el centro de operaciones de la aplicación y contiene:
- **Una imagen grande**.
- **Seis imágenes/botones** que realizan las siguientes acciones al ser pulsados:
  - **Imagen Alarma**: Crea una alarma en el dispositivo.
  - **Imagen GitHub**: Redirige a la página web de GitHub.
  - **Imagen Maps**: Abre Google Maps.
  - **Imagen Chistes**: Lanza un `intent` hacia la `activity` del cuenta chistes.
  - **Imagen Dados**: Lanza un `intent` hacia el juego de dados.
  - **Imagen TeléfonoSOS**: Lanza un `intent` hacia la `activity` para configurar una llamada de emergencia.

---

### 4. Juego de Dados
El juego se puede acceder desde la `MainActivity` pulsando la imagen del dado. El juego de dados tiene las siguientes características:

- **Botón de Estado del Juego**: Un `Switch` que, cuando está activado, muestra el vaso de dados. Si no, el vaso se oculta.
  
#### Instrucciones del Juego
1. **Objetivo del Juego**: Gana si acumulas 3 puntos. Pierdes si acumulas 3 tiradas fallidas.
2. **Cómo Jugar**:
   - **Inicio del Juego**: Verás tres vasos y un vaso de dados.
   - **Tocar un Vaso**: Descubrirás los números aleatorios de los dados (del 1 al 6).
3. **Reglas para Puntuar**:
   - Tres dados con el mismo número: Ganas 2 puntos.
   - Dos dados con el mismo número: Ganas 1 punto.
   - Ningún dado con el mismo número: Se cuenta como una tirada fallida.
4. **Cómo Ganar o Perder**:
   - **Ganar**: Si acumulas 3 puntos.
   - **Perder**: Si acumulas 3 tiradas fallidas.
5. **Botón de Reinicio**: Reinicia el juego desde cero.

---

### 4.1 Instrucciones
En el juego de dados, hay un botón en la esquina superior izquierda que redirige a una `activity` con las instrucciones del juego, presentadas visualmente para facilitar su comprensión.

---

### 5. Cuenta Chistes
En la `MainActivity`, al pulsar la imagen correspondiente, se accede mediante un `intent` a la `activity` de cuenta chistes. Esta tiene un botón con las siguientes funcionalidades:
- **Pulsar una vez**: Explica lo que hace el botón (contar un chiste).
- **Pulsar dos veces**: Se cuenta un chiste aleatorio.
- Mientras se cuenta el chiste, el botón se oculta y se muestra una `ProgressBar` hasta que termina la narración.

---

### 6. Teléfono SOS
Desde la `MainActivity`, un `intent` explícito lleva al `activity` del Teléfono SOS. Allí se solicita un número de teléfono válido de España. Al intentar realizar la llamada:
- Si los permisos no están activados, la aplicación redirige al usuario a la configuración para activarlos.
- Si los permisos ya están activados, se procede con la llamada.


