# Azure Credentials
# ====================
# Las credenciales se cargan desde variables de entorno:
# ARM_SUBSCRIPTION_ID, ARM_CLIENT_ID, ARM_CLIENT_SECRET, ARM_TENANT_ID
# No definas credenciales aquí (INSEGURO)

# Resource Group
variable "resource_group_name" {
  type        = string
  default     = "resource-group-maria-jose-olivero"
  description = "Name of the existing resource group"
}

variable "location" {
  type        = string
  default     = "eastus"
  description = "Azure region"
}

# Virtual Network
variable "vnet_name" {
  type        = string
  default     = "vnet-calculadora"
  description = "Name of the virtual network"
}

variable "address_space" {
  type        = list(string)
  default     = ["10.0.0.0/16"]
  description = "Address space for VNet"
}

# Subnet
variable "subnet_name" {
  type        = string
  default     = "subnet-calculadora"
  description = "Name of the subnet"
}

variable "subnet_address_prefix" {
  type        = list(string)
  default     = ["10.0.1.0/24"]
  description = "Address prefix for subnet"
}

# Network Interface
variable "nic_name" {
  type        = string
  default     = "nic-calculadora"
  description = "Name of the network interface"
}

# Public IP
variable "public_ip_name" {
  type        = string
  default     = "pip-calculadora"
  description = "Name of the public IP"
}

# Network Security Group
variable "nsg_name" {
  type        = string
  default     = "nsg-calculadora"
  description = "Name of the network security group"
}

# Virtual Machine
variable "vm_name" {
  type        = string
  default     = "vm-calculadora"
  description = "Name of the virtual machine"
}

variable "vm_size" {
  type        = string
  default     = "Standard_B2s"
  description = "Size of the VM"
}

variable "admin_username" {
  type        = string
  default     = "azureuser"
  description = "Admin username for the VM"
}

variable "ssh_public_key" {
  type        = string
  description = "Public SSH key content"
}

variable "public_key_path" {
  type        = string
  default     = "~/.ssh/azure_vm.pub"
  description = "Path to the public SSH key file"
}

# ACR & AKS Variables
variable "acr_name" {
  type    = string
  default = "acrmajolivero1772830059" # Debe ser único globalmente
}

variable "aks_cluster_name" {
  type    = string
  default = "aks-calculadora-majo"
}
