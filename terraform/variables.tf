# Azure Credentials
# ====================
# Las credenciales se cargan desde variables de entorno:
# ARM_SUBSCRIPTION_ID, ARM_CLIENT_ID, ARM_CLIENT_SECRET, ARM_TENANT_ID
# No definas credenciales aquí (INSEGURO)

# Resource Group
variable "resource_group_name" {
  type        = string
  description = "Name of the existing resource group"
}

variable "location" {
  type        = string
  description = "Azure region"
}

# Virtual Network
variable "vnet_name" {
  type        = string
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
  description = "Name of the network interface"
}

# Public IP
variable "public_ip_name" {
  type        = string
  description = "Name of the public IP"
}

# Network Security Group
variable "nsg_name" {
  type        = string
  description = "Name of the network security group"
}

# Virtual Machine
variable "vm_name" {
  type        = string
  description = "Name of the virtual machine"
}

variable "vm_size" {
  type        = string
  description = "Size of the VM"
}

variable "admin_username" {
  type        = string
  description = "Admin username for the VM"
}

variable "ssh_public_key" {
  type        = string
  description = "Public SSH key content"
}

variable "public_key_path" {
  type        = string
  description = "Path to the public SSH key file"
}

# ACR & AKS Variables
variable "acr_name" {
  type        = string
  description = "Name of the Azure Container Registry (must be globally unique)"
}

variable "aks_cluster_name" {
  type        = string
  description = "Name of the AKS cluster"
}

variable "aks_dns_prefix" {
  type        = string
  description = "DNS prefix for the AKS cluster"
}

variable "aks_node_count" {
  type        = number
  description = "Number of nodes in the AKS default node pool"
}

variable "aks_node_vm_size" {
  type        = string
  description = "VM size for AKS node pool"
}
