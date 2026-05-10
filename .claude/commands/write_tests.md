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

Lee el archivo $ARGUMENTS y genera tests unitarios completos para él.

Sigue estas reglas:

1. **Analiza la clase** — identifica todos los métodos públicos, sus parámetros, tipos de retorno y posibles excepciones.

2. **Escribe un test por escenario**, no uno por método. Para cada método cubre:
   - Happy path con valores representativos
   - Casos borde: null, cero, negativos, strings vacíos, listas vacías según aplique
   - Excepciones esperadas usando `assertThrows`

3. **Convenciones del proyecto**:
   - JUnit 5 (`@Test`, `@ExtendWith(MockitoExtension.class)`)
   - Usa `@SpringBootTest` solo si la clase requiere contexto de Spring
   - Nombres de método: `metodo_escenario_resultadoEsperado` (snake_case)
   - Sin comentarios que describan qué hace el test — el nombre debe ser suficiente

4. **Ubicación del archivo generado**: `src/test/java/` espejando el paquete del archivo fuente.

5. **Al finalizar**, indica la ruta exacta donde se creó el archivo de test y el porcentaje estimado de cobertura de ramas que aportarían estos tests.
