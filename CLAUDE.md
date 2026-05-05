# CLAUDE.md

This file provides guidance to Claude Code (claude.ai/code) when working with code in this repository.

## Project Overview

Spring Boot REST API (calculadora) deployed to Azure via a full DevOps pipeline: Terraform provisions infrastructure, Ansible deploys the JAR to an Azure VM, Docker images are pushed to ACR, and Helm deploys to AKS.

## Common Commands

### Build & Test (Java/Gradle)

```bash
./gradlew build                     # Compile, test, and produce JAR
./gradlew test                      # Run unit tests only
./gradlew jacocoTestReport          # Generate coverage report (HTML + XML)
./gradlew bootRun                   # Run Spring Boot locally on port 8080
./gradlew sonar                     # Run SonarCloud analysis (requires token)
```

Coverage reports land in `build/reports/jacoco/test/`.

### InSpec Compliance Tests

```bash
inspec exec compliance-testing/backstage-profile/ --chef-license accept-no-persist
```

Generates an XML report for Azure DevOps. The profile validates project structure (Dockerfile, Terraform files, Helm chart, Ansible files, Java source tree, documentation).

### Terraform

```bash
cd terraform
terraform init      # Backend: Azure Blob Storage (stmajo1773169612/tfstate)
terraform plan
terraform apply
terraform destroy
```

Credentials come from `ARM_*` environment variables or Azure CLI login.

### Ansible

```bash
cd ansible
./deploy.sh          # Interactive menu: full deploy, verbose, syntax check, task-specific runs
ansible-playbook playbook.yml -i inventory.ini   # Direct execution
```

Target VM: `172.182.232.165` (azureuser via SSH key `~/.ssh/azure_vm`).

### Docker

```bash
docker build --build-arg BUILD_ID=<id> -t calculadora .
```

Requires `build/libs/calculator-java-gradle-0.0.1-SNAPSHOT.jar` to exist first.

## Architecture

### Application Layer (`src/`)

- **Package**: `com.entrenamiento.Calculadora`
- **`controlador/controlador.java`**: 9 REST endpoints for math operations (suma, resta, multiplicación, división, raíz cuadrada, área/perímetro de círculo y cuadrado)
- **`controlador/VersionController.java`**: `/api/calculadora/version` health/version endpoint
- **`modelo/resultado.java`**: Response DTO

### CI/CD Pipeline (`pipeline/`)

`main-pipeline.yml` orchestrates 6 stages in order:

1. **SemanticVersioning** — GitVersion 6.0.x computes version from commit messages (`break:` = major, `feat:` = minor, `fix:` = patch)
2. **DevelopmentIntegration** — Gradle build → SonarCloud → JUnit + JaCoCo → publish artifact
3. **TerraformInfrastructure** — Provisions Azure VM, VNet, NSG, ACR, AKS
4. **ComplianceTesting** — InSpec profile against project structure
5. **TechnicalExcellence** — ShiftLeft SAST scan
6. **DockerArtifactManagement** — Docker build → ACR push → Grype scan
7. **KubernetesDeployment** — Helm install/upgrade to AKS

Pipeline uses the reusable template `pipeline/templates/base-pipeline.yml`. Secrets come from Azure DevOps Library group `calculadora-secrets-majo`. Token replacement (`#{TOKEN}#`) populates `.tfvars` and Helm `values.yaml` at runtime. Trigger is manual (`trigger: none`); agent pool is `Agent-Majo`.

### Infrastructure (`terraform/`)

- Azure Resource Group: `resource-group-maria-jose-olivero` (pre-existing, referenced as data source)
- VM: Standard_B2s, Ubuntu 20.04, SSH only, admin user `azureuser`
- NSG opens ports 22, 80, 443, 8080
- ACR: `acrmajolivero1772830059` (Basic SKU, admin enabled)
- AKS: `aks-calculadora-majo`, 1 node (Standard_B2s), System Assigned Identity
- Remote state in Azure Blob: `stmajo1773169612/tfstate/terraform.tfstate`

### Kubernetes (`helm/` and `k8s/`)

Helm chart at `helm/calculadora/` is the primary deployment method. `k8s/calculadora-deploy.yaml` is an alternative raw manifest. Both use token replacement for image tags and namespaces. Service type is ClusterIP (internal only). The ACR pull secret `acr-secret` must be pre-created in the cluster namespace.

### Compliance Testing (`compliance-testing/backstage-profile/`)

InSpec profile with 10 controls that verify the presence of all required project files. Controls run against the local filesystem (not a remote target). Impact levels: 1.0 for critical files (Dockerfile, pipeline, Terraform), 0.7 for Java classes, 0.5 for documentation.
