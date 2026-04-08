# Terraform Variables
# IMPORTANTE: Las credenciales NO van aquí


# VM Configuration
resource_group_name = "resource-group-maria-jose-olivero"
location            = "East US"
vnet_name           = "vnet-calculadora"
subnet_name         = "subnet-calculadora"
nic_name            = "nic-calculadora"
public_ip_name      = "pip-calculadora"
nsg_name            = "nsg-calculadora"
vm_name             = "vm-calculadora"
vm_size             = "Standard_B2s"
admin_username      = "azureuser"
public_key_path     = "~/.ssh/id_rsa.pub"
acr_name            = "acrmajolivero1772830059"
aks_cluster_name    = "aks-calculadora-majo"
aks_dns_prefix      = "aks-calc"
aks_node_count      = 1
aks_node_vm_size    = "Standard_B2s"
address_space       = ["10.0.0.0/16"]           #Rango total de direcciones IP que el Virtual Network puede usar. Tiene 65536 direcciones IP disponibles.
subnet_address_prefix = ["10.0.1.0/24"]         #Es un sub-rango dentro del Virtual Network que asignas a una Subnet específica.
ssh_public_key      = "ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAACAQCrfSmivyFF6T2p/r5uDlAo6kJTLPmsiNhWbureG9mBtUI+yg0xXLpqXh66wqeSOT0DZ2PQlptkGqlRM5Qqs1ZjM59qxXfXnx/7bYK8xBbqP929zgFjd3P+mq/kACfiBw9pkck0+ehdfJ0vGweC+pqzqoBRmcATqSUrnIRK++3bKcD03Fd/cEDjasRBuKN4mDzVihiE9pa6+cd09bsU+12ry7mRcTlZ+ZfBEaZ3rf2BFN5cphK+BDY8jHrW09HSz8xLs8CZJSBwaqI3Iw41MFnyrangxRZVJIHZsnVlanS0J+r4IlPTlZ6jNmLwEzguWwmyp1dw4yiR7vz4YusOxtHe9Pqs4RozA3CP+TaM3ceofvOzKsoC88hvZdBojT9rJP33qV/Ia8LJu0LkleX4oVIkkvZEjyJoDpt678u/72SLwsDXF0TCODKbxK6fXryl8xjN01WmyYidmOkukkj0m9wPqSd1HcSO7K0hhPb0QgnCOFSI+IZ7f3Q+5YGRV6B8fd3CPFhWtW2GagekeOrwiygJt6aRoRk7UWw04Gx3D31FjwdgAi0TYOzqSa6Xc+oxhvzi3GO3AYkX2V/yU/DNAFuJXxC/WK7i1gAGZl5EcWVr+rrZJnQ/MbDUrdYf7/qPsQl9niLX2+9y5qLRlOkDeHNbwnfwHzVUwqc7CxJzWcNz6w== azureuser@calculadora"
