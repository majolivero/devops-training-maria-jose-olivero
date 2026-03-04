# Playbook Ansible - Despliegue de Calculadora en Azure

## Descripción
Este playbook de Ansible automatiza:
1. ✅ Instalación de Java (OpenJDK 17)
2. ✅ Copia del JAR a la VM
3. ✅ Ejecución de la aplicación
4. ✅ Verificación de que la API funciona
5. ✅ Testing de endpoints REST

## Requisitos Previos
- Ansible instalado en tu máquina local ✓
- SSH acceso a la VM Azure ✓
- VM con Python instalado ✓
- JAR disponible en: `/home/adminquind/Descargas/calculator-java-gradle-0.0.1-SNAPSHOT (2).jar` ✓

## Estructura de Archivos
```
ansible/
├── inventory.ini          # Lista de servidores
├── playbook.yml           # Tareas a ejecutar
├── ansible.cfg            # Configuración de Ansible
└── README.md              # Esta documentación
```

## Configuración del Inventario

El archivo `inventory.ini` contiene:
```ini
[calculadora_vm]
vm_azure ansible_host=20.168.76.60 ansible_user=azureuser ansible_password=TestPassword123!@#
```

**Variables disponibles:**
- `ansible_host`: IP de la VM (20.168.130.67)
- `ansible_user`: Usuario SSH (azureuser)
- `app_port`: Puerto de la aplicación (8080)

## Cómo Ejecutar el Playbook

### Opción 1: Ejecución Básica
```bash
cd /home/adminquind/workspace/devops-training-maria-jose-olivero/ansible

# Ejecutar el playbook
ansible-playbook playbook.yml
```

### Opción 2: Con Verbose (Ver detalles)
```bash
# Nivel 1 de verbosidad (-v)
ansible-playbook playbook.yml -v

# Nivel 2 de verbosidad (-vv)
ansible-playbook playbook.yml -vv

# Nivel 3 de verbosidad (-vvv)
ansible-playbook playbook.yml -vvv
```

### Opción 3: Ejecución Específica de Tasks
```bash
# Ejecutar solo instalar Java
ansible-playbook playbook.yml -t "Instalar OpenJDK 11"

# Ejecutar solo copiar JAR
ansible-playbook playbook.yml -t "Copiar JAR a la VM"
```

### Opción 4: Syntax Check (Verificar sintaxis sin ejecutar)
```bash
# Verificar que el playbook sea válido
ansible-playbook playbook.yml --syntax-check
```

## Tareas que Ejecuta el Playbook

1. **Actualizar sistema**
   - `apt update` para obtener últimos paquetes

2. **Instalar Java**
   - Instala OpenJDK 11 (JRE + JDK)
   - Verifica versión instalada

3. **Crear directorios**
   - `/opt/apps/calculadora/` (directorio de la app)
   - `/var/log/apps/calculadora/` (directorio de logs)

4. **Copiar JAR**
   - Copia desde local a `/opt/apps/calculadora/calculator-0.0.1-SNAPSHOT.jar`
   - Verifica que se copió correctamente

5. **Crear script de inicio**
   - Crea `/opt/apps/calculadora/start.sh`
   - Script ejecuta: `java -jar calculadora.jar --server.port=8080`

6. **Ejecutar aplicación**
   - Inicia el JAR en background
   - Guarda PID en `/opt/apps/calculadora/app.pid`

7. **Esperar inicialización**
   - Espera hasta 30 segundos a que el puerto 8080 responda
   - Verifica conectividad

8. **Health Check**
   - Llamada HTTP a `/actuator/health`
   - Verifica que la aplicación está lista

9. **Testing de API**
   - Prueba endpoint `/api/calculadora/version`
   - Verifica respuesta

10. **Resumen Final**
    - Muestra información de despliegue exitoso
    - URLs para acceder a la aplicación

## Salida Esperada

```
PLAY [Desplegar aplicación Calculadora en VM Azure] **

TASK [Actualizar índice de paquetes] ***
ok: [vm_azure]

TASK [Instalar OpenJDK 11] ***
changed: [vm_azure]

TASK [Crear directorio de aplicación] ***
changed: [vm_azure]

TASK [Copiar JAR a la VM] ***
changed: [vm_azure]

TASK [Iniciar la aplicación Calculadora] ***
changed: [vm_azure]

...

TASK [Mostrar información de despliegue exitoso] ***
ok: [vm_azure] => {
    "msg": "✅ DESPLIEGUE EXITOSO\nHealth: http://20.168.76.60:8080/actuator/health\n..."
}

PLAY RECAP ****
vm_azure : ok=14 changed=8 unreachable=0 failed=0 skipped=0 rescued=0 ignored=0
```

## Acceder a la Aplicación

Después de ejecutar el playbook exitosamente:

### Health Check
```bash
curl http://20.168.76.60:8080/actuator/health
```

Respuesta esperada:
```json
{"status":"UP"}
```

### API de Calculadora
```bash
curl http://20.168.76.60:8080/api/calculadora/version
```

### Ver Logs en Tiempo Real
```bash
ssh azureuser@20.168.76.60
tail -f /var/log/apps/calculadora/app.log
```

### Ver Proceso Ejecutándose
```bash
ssh azureuser@20.168.76.60
ps aux | grep java
```

### Obtener PID de la Aplicación
```bash
ssh azureuser@20.168.76.60
cat /opt/apps/calculadora/app.pid
```

## Solución de Problemas

### Error: "Failed to connect to the host"
**Causa:** SSH no puede conectar a la VM
**Solución:**
```bash
# Verificar conectividad
ping 20.168.76.60

# Probar SSH manualmente
ssh azureuser@20.168.76.60
```

### Error: "Java not found"
**Causa:** Java no se instaló correctamente
**Solución:**
```bash
# Ejecutar solo la tarea de Java
ansible-playbook playbook.yml --tags "Instalar OpenJDK 11" -vv
```

### Error: "Permission denied" al copiar JAR
**Causa:** Usuario sin permisos en /opt/apps/
**Solución:** El playbook automáticamente crea directorios con permisos correctos

### La aplicación no inicia (timeout en puerto 8080)
**Causa:** La aplicación tardó más de 30s en iniciar
**Solución:**
1. Aumentar timeout en el playbook (modificar wait_for delay/timeout)
2. Ver logs: `ssh azureuser@20.168.76.60 tail -f /var/log/apps/calculadora/app.log`
3. Verificar que Java está instalado: `ssh azureuser@20.168.76.60 java -version`

## Variables Disponibles

Puedes sobrescribir variables desde línea de comandos:

```bash
# Cambiar puerto
ansible-playbook playbook.yml -e "app_port=8888"

# Cambiar host
ansible-playbook playbook.yml -e "ansible_host=10.0.0.5"

# Múltiples variables
ansible-playbook playbook.yml -e "app_port=9000 app_name=mi_app"
```

## Continuar Aprendiendo

- Documentación oficial: https://docs.ansible.com
- Best practices: https://docs.ansible.com/ansible/latest/user_guide/playbooks_best_practices.html
- Módulos disponibles: `ansible-doc -l`

