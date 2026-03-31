# Terraform Variables
# IMPORTANTE: Las credenciales NO van aquí


# VM Configuration
resource_group_name = "#{RESOURCE_GROUP}#"
location            = "#{LOCATION}#"
vnet_name           = "#{VNET_NAME}#"
subnet_name         = "#{SUBNET_NAME}#"
nic_name            = "#{NIC_NAME}#"
public_ip_name      = "#{PUBLIC_IP_NAME}#"
nsg_name            = "#{NSG_NAME}#"
vm_name             = "#{VM_NAME}#"
vm_size             = "#{VM_SIZE}#"
admin_username      = "#{ADMIN_USERNAME}#"
public_key_path     = "#{PUBLIC_KEY_PATH}#"
acr_name            = "#{ACR_NAME}#"
aks_cluster_name    = "#{AKS_CLUSTER_NAME}#"
aks_dns_prefix      = "#{AKS_DNS_PREFIX}#"
aks_node_count      = #{AKS_NODE_COUNT}#
aks_node_vm_size    = "#{AKS_NODE_VM_SIZE}#"
address_space       = ["10.0.0.0/16"]           #Rango total de direcciones IP que el Virtual Network puede usar. Tiene 65536 direcciones IP disponibles.
subnet_address_prefix = ["10.0.1.0/24"]         #Es un sub-rango dentro del Virtual Network que asignas a una Subnet específica.
