# Terraform Variables
# IMPORTANTE: Las credenciales NO van aquí


# VM Configuration
resource_group_name = "#{RESOURCE_GROUP}#"
location            = "#{LOCATION}#"
vnet_name           = "vnet-calculadora"
subnet_name         = "subnet-calculadora"
nic_name            = "nic-calculadora"
public_ip_name      = "pip-calculadora"
nsg_name            = "nsg-calculadora"
vm_name             = "vm-calculadora"
vm_size             = "#{VM_SIZE}#"
admin_username      = "#{ADMIN_USERNAME}#"
public_key_path     = "~/.ssh/azure_vm.pub"
address_space       = ["10.0.0.0/16"]           #Rango total de direcciones IP que el Virtual Network puede usar. Tiene 65536 direcciones IP disponibles.
subnet_address_prefix = ["10.0.1.0/24"]         #Es un sub-rango dentro del Virtual Network que asignas a una Subnet específica.
