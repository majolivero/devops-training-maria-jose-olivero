<!--
  ARCHIVO: write_tests.md
  UBICACIÓN: .claude/commands/write_tests.md

  ¿Qué es este archivo?
  ─────────────────────
  Este archivo define un COMANDO PERSONALIZADO para Claude Code.
  El nombre del archivo (write_tests) determina cómo se invoca el comando:

      /write_tests

  ¿Cómo funciona?
  ───────────────
  Todo el texto que escribas DEBAJO de estos comentarios es el PROMPT
  que Claude ejecutará automáticamente cuando uses /write_tests en el chat.

  Puedes usar $ARGUMENTS para recibir parámetros al invocar el comando.
  Ejemplo de invocación con argumento:

      /write_tests src/main/java/com/entrenamiento/Calculadora/controlador/controlador.java

  Dentro del prompt, $ARGUMENTS se reemplaza por la ruta del archivo a testear.

  ¿Para qué sirve este comando en particular?
  ────────────────────────────────────────────
  Genera tests unitarios con JUnit 5 y Mockito para una clase Java del proyecto.
  Lee el archivo fuente indicado, identifica todos los métodos públicos y escribe
  tests que cubran:
    - El caso feliz (happy path) de cada método
    - Casos borde (null, cero, negativos, divisiones por cero, etc.)
    - Casos de error esperados (excepciones)

  Los tests siguen las convenciones del proyecto:
    - Ubicación: src/test/java/... (espejando el paquete del archivo fuente)
    - Framework: JUnit 5 (@Test, @ExtendWith) + Spring Boot Test si aplica
    - Cobertura: apunta a cubrir todas las ramas para que JaCoCo reporte >= 80%

  IMPORTANTE: Todo lo que está dentro de estos comentarios HTML
  (<!-- ... -->) NO se ejecuta. El prompt real empieza después.

  EJEMPLO DE USO:
      /write_tests src/main/java/com/entrenamiento/Calculadora/controlador/controlador.java
-->

Lee el archivo $ARGUMENTS y determina qué tests hacen falta antes de escribir cualquier código.

Sigue estos pasos en orden:

1. **Detecta el archivo de test existente** — deriva la ruta espejando el paquete en `src/test/java/`. Si el archivo existe, léelo completo. Si no existe, continúa al paso 2 directamente.

2. **Analiza la clase fuente** — identifica todos los métodos públicos, sus parámetros, tipos de retorno y posibles excepciones.

3. **Determina la brecha de cobertura** — compara los métodos de la clase contra los tests existentes (incluyendo los comentados con `/* */`):
   - Si **todos los métodos tienen cobertura suficiente** (happy path + casos borde + errores): reporta el estado actual y no generes nada más.
   - Si **hay métodos sin tests o con cobertura parcial**: lista exactamente qué falta antes de proceder.

4. **Escribe solo los tests faltantes** — no dupliques tests que ya existen. Para cada método sin cobertura escribe:
   - Happy path con valores representativos
   - Casos borde: null, cero, negativos, strings vacíos según aplique
   - Excepciones esperadas usando `assertThrows`

5. **Convenciones del proyecto**:
   - JUnit 5 (`@Test`, `@ExtendWith(MockitoExtension.class)`)
   - Usa `@SpringBootTest` solo si la clase requiere contexto de Spring
   - Nombres de método: `metodo_escenario_resultadoEsperado` (snake_case)
   - Sin comentarios que describan qué hace el test — el nombre debe ser suficiente

6. **Al finalizar**, indica:
   - Si se generaron tests nuevos: la ruta del archivo y los métodos que ahora quedan cubiertos.
   - Si no se generaron: por qué la cobertura existente ya es suficiente.
