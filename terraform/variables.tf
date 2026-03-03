# Azure Credentials
variable "subscription_id" {
  type        = string
  description = "Azure Subscription ID"
}

variable "client_id" {
  type        = string
  description = "Azure Client ID (App ID)"
}

variable "client_secret" {
  type        = string
  sensitive   = true
  description = "Azure Client Secret"
}

variable "tenant_id" {
  type        = string
  description = "Azure Tenant ID"
}

# Resource Group
variable "resource_group_name" {
  type        = string
  default     = "rg-calculadora-dev"
  description = "Name of the resource group"
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

variable "admin_password" {
  type        = string
  sensitive   = true
  description = "Admin password for the VM"
}
