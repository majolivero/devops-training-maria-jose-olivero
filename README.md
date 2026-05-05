# 🧮 Calculadora Java + Terraform + Ansible

Aplicación Spring Boot de Calculadora desplegada en Azure VM usando Terraform (Infraestructura) e Ansible (Configuración).

---

## 📋 Tabla de Contenidos

- [Características](#características)
- [Operaciones Disponibles](#operaciones-disponibles)
- [URLs de Acceso](#urls-de-acceso)
- [Getting Started](#getting-started)
- [Build and Test](#build-and-test)
- [Deployment](#deployment)
- [Infraestructura](#infraestructura)
- [Soporte](#soporte)

---

## ✨ Características

- ✅ **9 operaciones matemáticas** (suma, resta, multiplicación, división, raíz, áreas y perímetros)
- ✅ **Spring Boot** REST API
- ✅ **Terraform** para infraestructura IaC (Infrastructure as Code)
- ✅ **Ansible** para automatización del despliegue
- ✅ **Azure VM** Standard_B2s con Ubuntu 20.04 LTS
- ✅ **Seguridad**: SSH key-based authentication, NSG firewall
- ✅ **Soporte para decimales** con precisión decimal
- ✅ **Validación robusta** de entrada de datos
- ✅ **Testing automatizado** con JUnit

---

## 📐 Operaciones Disponibles

| # | Operación | Endpoint | Parámetros | Ejemplo |
|---|-----------|----------|-----------|---------|
| 1️⃣ | **Suma** | `/sumar` | `op1`, `op2` | `?op1=10&op2=5` → 15.0 |
| 2️⃣ | **Resta** | `/restar` | `minuendo`, `sustraendo` | `?minuendo=20&sustraendo=8` → 12.0 |
| 3️⃣ | **Multiplicación** | `/multiplicar` | `multiplicando`, `multiplicador` | `?multiplicando=6&multiplicador=7` → 42.0 |
| 4️⃣ | **División** | `/dividir` | `dividendo`, `divisor` | `?dividendo=100&divisor=4` → 25.0 |
| 5️⃣ | **Raíz Cuadrada** | `/raiz` | `radicando` | `?radicando=144` → 12.0 |
| 6️⃣ | **Perímetro Círculo** | `/perimetroCircle` | `radio` | `?radio=5` → 31.4 |
| 7️⃣ | **Área Cuadrado** | `/areaCuadrado` | `lado` | `?lado=8` → 64.0 |
| 8️⃣ | **Perímetro Cuadrado** | `/perimetroCuadrado` | `lado` | `?lado=5` → 20.0 |
| 9️⃣ | **Área Círculo** | `/areaCircle` | `radio` | `?radio=3` → 28.3 |

---

## 🌐 URLs de Acceso

**API Base**: `http://172.182.232.165:8080`

### Ejemplos con navegador

```
http://172.182.232.165:8080/sumar?op1=10&op2=5
http://172.182.232.165:8080/multiplicar?multiplicando=7&multiplicador=8
http://172.182.232.165:8080/raiz?radicando=25
```

### Ejemplos con curl (terminal)

```bash
# Suma
curl http://172.182.232.165:8080/sumar?op1=10&op2=5

# Resta
curl http://172.182.232.165:8080/restar?minuendo=20&sustraendo=8

# Raíz Cuadrada
curl http://172.182.232.165:8080/raiz?radicando=100

# Con decimales
curl http://172.182.232.165:8080/sumar?op1=3.5&op2=2.7

# Formateado
curl -s 'http://172.182.232.165:8080/sumar?op1=10&op2=5' | jq .
```

---

## 🚀 Getting Started

### Requisitos previos

```bash
# Instalar herramientas
- Java 17+
- Gradle 7.0+
- Terraform v1.14.6+
- Ansible v2.16.3+
- Azure CLI
- SSH keys (~/.ssh/azure_vm)
```

### Instalación local

```bash
# Clonar repositorio
git clone <repo-url>
cd devops-training-maria-jose-olivero

# Instalar dependencias (Maven/Gradle)
./gradlew build
```

---

## 🏗️ Build and Test

### Compilar la aplicación

```bash
# Build con Gradle
./gradlew build

# Build sin tests
./gradlew build -x test

# Build JAR ejecutable
./gradlew bootJar
```

### Ejecutar tests

```bash
# Todos los tests
./gradlew test

# Tests específicos
./gradlew test --tests CalculadoraApplicationTests

# Con cobertura de código
./gradlew test jacocoTestReport
```

### Ejecutar localmente

```bash
# Opción 1: Con Gradle
./gradlew bootRun

# Opción 2: JAR generado
java -jar build/libs/calculator-java-gradle-0.0.1-SNAPSHOT.jar

# Ver disponible en http://localhost:8080/version
```

---

## 🎯 Deployment

### Despliegue Completo (Infraestructura + Aplicación)

```bash
# 1. Crear infraestructura con Terraform
cd terraform
terraform plan
terraform apply -auto-approve

# 2. Desplegar aplicación con Ansible
cd ../ansible
ansible-playbook playbook.yml

# 3. Verificar que está funcionando
curl http://172.182.232.165:8080/version
```

### Re-desplegar la aplicación (sin recrear VM)

```bash
cd ansible
ansible-playbook playbook.yml
```

### Destruir infraestructura

```bash
cd terraform
terraform destroy -auto-approve
```

⚠️ **Advertencia**: Esto elimina la VM y todos los recursos. Detendrá incurrencia de costos en Azure.

---

## 🏗️ Infraestructura

### Azure Resources

- **Máquina Virtual**: Standard_B2s (2 vCPU, 4GB RAM)
- **SO**: Ubuntu 20.04 LTS  
- **IP Pública**: 172.182.232.165
- **Java Runtime**: OpenJDK 17.0.15
- **Puerto Aplicación**: 8080 (abierto en NSG)
- **Autenticación**: SSH key-based (~/.ssh/azure_vm)

### Terraform IaC

Infraestructura como Código con arquivos en:
- `terraform/main.tf` - Recursos Azure
- `terraform/variables.tf` - Variables
- `terraform/terraform.tfvars` - Valores

Recursos creados:
- Azure VM
- VNet y Subnet
- Network Security Group (NSG) con reglas de firewall
- IP Pública
- NIC (Network Interface Card)

### Ansible Automation

Automatización y configuración:
- `ansible/playbook.yml` - 19 tareas de despliegue
- `ansible/inventory.ini` - Hosts y variables
- `ansible/ansible.cfg` - Configuración runtime
- `ansible/README.md` - Documentación detallada

### Seguridad

✅ **SSH Key-based Authentication**
- No passwords en configuración
- Credenciales en `~/.ssh/azure_vm`

✅ **Azure NSG Firewall**
- Puerto 22 (SSH): Abierto
- Puerto 80 (HTTP): Abierto
- Puerto 443 (HTTPS): Abierto
- Puerto 8080 (App): Abierto
- Otros: Cerrados

✅ **IaC con Terraform**
- Infraestructura reproducible
- Version controlada
- Cambios auditables

✅ **Secretos seguros**
- Sin hardcoding de credenciales
- Variables de entorno
- `.gitignore` configurado

---

## 📂 Estructura de Archivos

```
.
├── src/
│   ├── main/
│   │   ├── java/com/entrenamiento/Calculadora/
│   │   │   ├── CalculadoraApplication.java
│   │   │   ├── controlador/
│   │   │   │   ├── controlador.java (9 operaciones)
│   │   │   │   └── VersionController.java
│   │   │   └── modelo/
│   │   │       └── resultado.java
│   │   └── resources/
│   │       └── application.properties
│   └── test/
│       └── java/com/entrenamiento/Calculadora/
│           └── CalculadoraApplicationTests.java
├── terraform/
│   ├── main.tf
│   ├── variables.tf
│   ├── terraform.tfvars
│   └── terraform.tfstate
├── ansible/
│   ├── playbook.yml
│   ├── inventory.ini
│   ├── ansible.cfg
│   └── README.md
├── build.gradle
├── settings.gradle
└── README.md (este archivo)
```

---

## 📊 Ejemplos de Casos de Uso

### Cálculos Matemáticos

```bash
# Calculadora financiera: 1500 × 12 meses
curl "http://172.182.232.165:8080/multiplicar?multiplicando=1500&multiplicador=12"
# Resultado: 18000.0

# Geometría: Área de círculo con radio 10
curl "http://172.182.232.165:8080/areaCircle?radio=10"
# Resultado: 314.2

# Física: √9.81 (aceleración de gravedad)
curl "http://172.182.232.165:8080/raiz?radicando=9.81"
# Resultado: 3.13
```

### Integración con otros sistemas

```bash
# Desde Python
import requests
response = requests.get('http://172.182.232.165:8080/sumar?op1=10&op2=5')
result = response.json()
print(result['resultado'])  # 15.0

# Desde JavaScript
fetch('http://172.182.232.165:8080/sumar?op1=10&op2=5')
  .then(r => r.json())
  .then(data => console.log(data.resultado))  // 15.0
```

---

## 📞 Soporte

### Ver logs de la aplicación

```bash
ssh -i ~/.ssh/azure_vm azureuser@172.182.232.165
tail -f /var/log/apps/calculadora/app.log
```

### Re-ejecutar playbook

```bash
cd ansible && ansible-playbook playbook.yml
```

### Verificar proceso Java

```bash
ssh -i ~/.ssh/azure_vm azureuser@172.182.232.165
ps aux | grep java
```

### Casos de error

**División por cero:**
```bash
curl http://172.182.232.165:8080/dividir?dividendo=10&divisor=0
# {"resultado":null,"estado":"Error: No es posible dividir por cero"}
```

**Raíz de número negativo:**
```bash
curl http://172.182.232.165:8080/raiz?radicando=-4
# {"resultado":null,"estado":"Error: No se puede calcular la raíz cuadrada de un número negativo"}
```

---

## 📝 Licencia

Proyecto educativo de DevOps con Java + Cloud.

**Autor**: María José Olivero  
**Fecha**: Marzo 2026  
**Versión**: 0.0.1-SNAPSHOT

---

## 🙏 Contributing

Las contribuciones son bienvenidas. Para cambios importantes, abre un issue primero para discutir qué te gustaría cambiar.