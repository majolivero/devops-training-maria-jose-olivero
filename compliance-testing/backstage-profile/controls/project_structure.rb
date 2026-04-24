# =============================================================================
# Control: Estructura raíz del proyecto
# =============================================================================
control 'project-root-files' do
  impact 1.0
  title 'Archivos obligatorios en la raíz del proyecto'
  desc 'El proyecto debe contener los archivos de configuración base en la raíz.'

  describe file('.') do
    it { should be_directory }
  end

  describe file('Dockerfile') do
    it { should exist }
    it { should be_file }
  end

  describe file('build.gradle') do
    it { should exist }
    it { should be_file }
  end

  describe file('settings.gradle') do
    it { should exist }
    it { should be_file }
  end

  describe file('GitVersion.yml') do
    it { should exist }
    it { should be_file }
  end

  describe file('README.md') do
    it { should exist }
    it { should be_file }
  end

  describe file('gradlew') do
    it { should exist }
    it { should be_file }
  end
end

# =============================================================================
# Control: Carpeta pipeline
# =============================================================================
control 'project-pipeline-structure' do
  impact 1.0
  title 'El pipeline principal debe existir dentro de la carpeta pipeline/'
  desc 'El archivo main-pipeline.yml debe estar dentro de la carpeta pipeline/.'

  describe file('pipeline') do
    it { should exist }
    it { should be_directory }
  end

  describe file('pipeline/main-pipeline.yml') do
    it { should exist }
    it { should be_file }
  end
end

# =============================================================================
# Control: Código fuente Java
# =============================================================================
control 'project-src-structure' do
  impact 1.0
  title 'La estructura del código fuente Java debe seguir el estandar Maven/Gradle'
  desc 'El código fuente debe estar en src/main/java y los tests en src/test/java.'

  describe file('src') do
    it { should exist }
    it { should be_directory }
  end

  describe file('src/main') do
    it { should exist }
    it { should be_directory }
  end

  describe file('src/main/java') do
    it { should exist }
    it { should be_directory }
  end

  describe file('src/main/resources') do
    it { should exist }
    it { should be_directory }
  end

  describe file('src/main/resources/application.properties') do
    it { should exist }
    it { should be_file }
  end

  describe file('src/test') do
    it { should exist }
    it { should be_directory }
  end

  describe file('src/test/java') do
    it { should exist }
    it { should be_directory }
  end
end

# =============================================================================
# Control: Clases de la aplicación Java
# =============================================================================
control 'project-java-classes' do
  impact 0.7
  title 'Las clases Java deben estar en el paquete correcto'
  desc 'Las clases deben seguir la estructura de paquetes com.entrenamiento.Calculadora.'

  base_path = 'src/main/java/com/entrenamiento/Calculadora'

  describe file(base_path) do
    it { should exist }
    it { should be_directory }
  end

  describe file("#{base_path}/CalculadoraApplication.java") do
    it { should exist }
    it { should be_file }
  end

  describe file("#{base_path}/controlador") do
    it { should exist }
    it { should be_directory }
  end

  describe file("#{base_path}/controlador/controlador.java") do
    it { should exist }
    it { should be_file }
  end

  describe file("#{base_path}/controlador/VersionController.java") do
    it { should exist }
    it { should be_file }
  end

  describe file("#{base_path}/modelo") do
    it { should exist }
    it { should be_directory }
  end

  describe file("#{base_path}/modelo/resultado.java") do
    it { should exist }
    it { should be_file }
  end
end

# =============================================================================
# Control: Infraestructura Terraform
# =============================================================================
control 'project-terraform-structure' do
  impact 1.0
  title 'Los archivos de Terraform deben estar en la carpeta terraform/'
  desc 'Todos los archivos .tf deben residir dentro de la carpeta terraform/.'

  describe file('terraform') do
    it { should exist }
    it { should be_directory }
  end

  describe file('terraform/main.tf') do
    it { should exist }
    it { should be_file }
  end

  describe file('terraform/variables.tf') do
    it { should exist }
    it { should be_file }
  end

  describe file('terraform/outputs.tf') do
    it { should exist }
    it { should be_file }
  end

  describe file('terraform/provider.tf') do
    it { should exist }
    it { should be_file }
  end

  describe file('terraform/terraform.tfvars') do
    it { should exist }
    it { should be_file }
  end
end

# =============================================================================
# Control: Helm Charts
# =============================================================================
control 'project-helm-structure' do
  impact 1.0
  title 'Los Helm charts deben estar en la carpeta helm/calculadora/'
  desc 'La estructura del chart de Helm debe seguir el estandar con Chart.yaml, values.yaml y templates/.'

  describe file('helm') do
    it { should exist }
    it { should be_directory }
  end

  describe file('helm/calculadora') do
    it { should exist }
    it { should be_directory }
  end

  describe file('helm/calculadora/Chart.yaml') do
    it { should exist }
    it { should be_file }
  end

  describe file('helm/calculadora/values.yaml') do
    it { should exist }
    it { should be_file }
  end

  describe file('helm/calculadora/templates') do
    it { should exist }
    it { should be_directory }
  end

  describe file('helm/calculadora/templates/deployment.yaml') do
    it { should exist }
    it { should be_file }
  end

  describe file('helm/calculadora/templates/service.yaml') do
    it { should exist }
    it { should be_file }
  end
end

# =============================================================================
# Control: Kubernetes manifests
# =============================================================================
control 'project-k8s-structure' do
  impact 1.0
  title 'Los manifests de Kubernetes deben estar en la carpeta k8s/'
  desc 'Los archivos de deploy de Kubernetes deben estar en k8s/.'

  describe file('k8s') do
    it { should exist }
    it { should be_directory }
  end

  describe file('k8s/calculadora-deploy.yaml') do
    it { should exist }
    it { should be_file }
  end
end

# =============================================================================
# Control: Ansible
# =============================================================================
control 'project-ansible-structure' do
  impact 0.7
  title 'Los archivos de Ansible deben estar en la carpeta ansible/'
  desc 'El playbook y la configuracion de Ansible deben residir en ansible/.'

  describe file('ansible') do
    it { should exist }
    it { should be_directory }
  end

  describe file('ansible/playbook.yml') do
    it { should exist }
    it { should be_file }
  end

  describe file('ansible/inventory.ini') do
    it { should exist }
    it { should be_file }
  end

  describe file('ansible/ansible.cfg') do
    it { should exist }
    it { should be_file }
  end
end

# =============================================================================
# Control: Documentación
# =============================================================================
control 'project-documentation' do
  impact 0.5
  title 'La documentacion del proyecto debe existir en la carpeta documentacion/'
  desc 'El proyecto debe tener documentacion adicional en la carpeta documentacion/.'

  describe file('documentacion') do
    it { should exist }
    it { should be_directory }
  end

  describe file('documentacion/readme.md') do
    it { should exist }
    it { should be_file }
  end
end
