# Data source para usar el Resource Group EXISTENTE
# (No lo creamos, solo lo referenciamos)
data "azurerm_resource_group" "rg" {
  name = var.resource_group_name
}

# Virtual Network - YA EXISTE EN AZURE
# resource "azurerm_virtual_network" "vnet" {
#   name                = var.vnet_name
#   address_space       = var.address_space
#   location            = data.azurerm_resource_group.rg.location
#   resource_group_name = data.azurerm_resource_group.rg.name
# }

# Subnet
resource "azurerm_subnet" "subnet" {
  name                 = var.subnet_name
  resource_group_name  = data.azurerm_resource_group.rg.name        #Usamos un RG ya existente (mi RG)
  virtual_network_name = azurerm_virtual_network.vnet.name
  address_prefixes     = var.subnet_address_prefix
}

# Network Interface
resource "azurerm_network_interface" "nic" {
  name                = var.nic_name
  location            = data.azurerm_resource_group.rg.location      #Network Interface usa la ubicación del RG existente
  resource_group_name = data.azurerm_resource_group.rg.name          #Se usa el RG existente

  ip_configuration {
    name                          = "testConfiguration"
    subnet_id                     = azurerm_subnet.subnet.id
    private_ip_address_allocation = "Dynamic"
    public_ip_address_id          = azurerm_public_ip.public_ip.id
  }
}

# Public IP Address - YA EXISTE EN AZURE
# resource "azurerm_public_ip" "public_ip" {
#   name                = var.public_ip_name
#   location            = data.azurerm_resource_group.rg.location     #Pasa lo mismo que en linea 26 y 27
#   resource_group_name = data.azurerm_resource_group.rg.name
#   allocation_method   = "Static"
# }

# Network Security Group
# Network Security Group - YA EXISTE EN AZURE
# resource "azurerm_network_security_group" "nsg" {
#   name                = var.nsg_name
#   location            = data.azurerm_resource_group.rg.location    #Pasa lo mismo que en linea 26 y 27
#   resource_group_name = data.azurerm_resource_group.rg.name
#
#   security_rule {
#     name                       = "AllowSSH"
#     priority                   = 100
#     direction                  = "Inbound"
#     access                     = "Allow"
#     protocol                   = "Tcp"
#     source_port_range          = "*"
#     destination_port_range     = "22"
#     source_address_prefix      = "*"
#     destination_address_prefix = "*"
#   }
#
#   security_rule {
#     name                       = "AllowHTTP"
#     priority                   = 101
#     direction                  = "Inbound"
#     access                     = "Allow"
#     protocol                   = "Tcp"
#     source_port_range          = "*"
#     destination_port_range     = "80"
#     source_address_prefix      = "*"
#     destination_address_prefix = "*"
#   }
#
#   security_rule {
#     name                       = "AllowHTTPS"
#     priority                   = 102
#     direction                  = "Inbound"
#     access                     = "Allow"
#     protocol                   = "Tcp"
#     source_port_range          = "*"
#     destination_port_range     = "443"
#     source_address_prefix      = "*"
#     destination_address_prefix = "*"
#   }
#
#   security_rule {
#     name                       = "AllowAppPort"
#     priority                   = 103
#     direction                  = "Inbound"
#     access                     = "Allow"
#     protocol                   = "Tcp"
#     source_port_range          = "*"
#     destination_port_range     = "8080"
#     source_address_prefix      = "*"
#     destination_address_prefix = "*"
#   }
# }

# Associate NSG with Subnet
resource "azurerm_subnet_network_security_group_association" "nsg_association" {
  subnet_id                 = azurerm_subnet.subnet.id
  network_security_group_id = azurerm_network_security_group.nsg.id
}

# Virtual Machine
resource "azurerm_linux_virtual_machine" "vm" {
  name                = var.vm_name
  location            = data.azurerm_resource_group.rg.location     #VM usa info del RG existente, porque la VM se va a crear aquí
  resource_group_name = data.azurerm_resource_group.rg.name
  size                = var.vm_size

  disable_password_authentication = true
  admin_username                  = var.admin_username

  admin_ssh_key {
    username   = var.admin_username
    public_key = var.ssh_public_key
  }

  network_interface_ids = [
    azurerm_network_interface.nic.id,
  ]

  os_disk {
    caching              = "ReadWrite"
    storage_account_type = "Premium_LRS"
  }

  source_image_reference {
    publisher = "Canonical"
    offer     = "0001-com-ubuntu-server-focal"
    sku       = "20_04-lts-gen2"
    version   = "latest"
  }
}

# 1. Azure Container Registry (ACR) - YA EXISTE EN AZURE
# resource "azurerm_container_registry" "acr" {
#   name                = var.acr_name
#   resource_group_name = data.azurerm_resource_group.rg.name
#   location            = data.azurerm_resource_group.rg.location
#   sku                 = "Basic"
#   admin_enabled       = true
# }

# 2. Azure Kubernetes Service (AKS) - YA EXISTE EN AZURE
# resource "azurerm_kubernetes_cluster" "aks" {
#   name                = var.aks_cluster_name
#   location            = data.azurerm_resource_group.rg.location
#   resource_group_name = data.azurerm_resource_group.rg.name
#   dns_prefix          = var.aks_dns_prefix
#
#   default_node_pool {
#     name       = "default"
#     node_count = var.aks_node_count
#     vm_size    = var.aks_node_vm_size
#   }
#
#   identity {
#     type = "SystemAssigned"
#   }
#
#   tags = {
#     Environment = "Dev"
#   }
# }

# 3. Permiso para que AKS pueda jalar imágenes del ACR (Role Assignment)
#resource "azurerm_role_assignment" "aks_acr_pull" {
#  principal_id                     = azurerm_kubernetes_cluster.aks.kubelet_identity[0].object_id
#  role_definition_name             = "AcrPull"
#  scope                            = azurerm_container_registry.acr.id
#  skip_service_principal_aad_check = true
#}

