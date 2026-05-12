<!--
  ARCHIVO: audit.md
  UBICACIÓN: .claude/commands/audit.md

  ¿Qué es este archivo?
  ─────────────────────
  Este archivo define un COMANDO PERSONALIZADO para Claude Code.
  El nombre del archivo (audit) determina cómo se invoca el comando:

      /audit

  ¿Cómo funciona?
  ───────────────
  Todo el texto que escribas DEBAJO de estos comentarios es el PROMPT
  que Claude ejecutará automáticamente cuando uses /audit en el chat.

  Puedes usar $ARGUMENTS para recibir parámetros al invocar el comando.
  Ejemplo de invocación con argumento:

      /audit pipeline/main-pipeline.yml

  Dentro del prompt, $ARGUMENTS se reemplaza por "pipeline/main-pipeline.yml".

  ¿Para qué sirve este comando en particular?
  ────────────────────────────────────────────
  Aquí defines qué quieres que Claude haga cuando escribas /audit.
  Puedes usarlo para auditar archivos del pipeline, revisar controles
  de InSpec, analizar configuraciones de Terraform, etc.

  IMPORTANTE: Todo lo que está dentro de estos comentarios HTML
  (<!-- ... -->) NO se ejecuta. El prompt real empieza después.

  EJEMPLO DE USO:
      /audit pipeline/main-pipeline.yml
      /audit pipeline/main-pipeline.yml terraform/variables.tf


    AUDITAR VARIOS ARCHIVOS A LA VEZ 
     No hay un límite técnico fijo — $ARGUMENTS es solo texto plano. El límite real es práctico:

    - Ventana de contexto: cada archivo que Claude lee consume tokens. Con archivos grandes como main-pipeline.yml, puedes saturar el contexto con 5–10 archivos pesados.
    - Calidad del análisis: con muchos archivos a la vez, la auditoría se vuelve más superficial. Para archivos críticos, mejor auditarlos en grupos pequeños.

    Regla práctica: 3–5 archivos por invocación es un buen balance. Si son archivos pequeños como variables.tf, puedes pasar más sin problema.
-->

Audita cada uno de los archivos listados en "$ARGUMENTS" (pueden ser uno o varios, separados por espacios) y reporta lo siguiente por cada archivo:

1. **Tokens sin reemplazar** — busca patrones `#{...}#` que estén hardcodeados en lugar de venir de variables.
2. **Stages o jobs sin `displayName`** — toda tarea debe tener nombre descriptivo.
3. **Tareas sin `condition`** — verifica si alguna etapa crítica debería tener condición explícita y no la tiene.
4. **Secretos expuestos** — detecta valores sensibles escritos directamente (contraseñas, tokens, claves).
5. **Dependencias entre stages** — verifica que los `dependsOn` sean consistentes con el orden lógico del pipeline.

Para cada hallazgo indica: archivo, número de línea aproximado y una recomendación concreta.
