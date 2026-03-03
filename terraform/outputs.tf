output "resource_group_id" {
  value       = azurerm_resource_group.rg.id
  description = "ID of the resource group"
}

output "resource_group_name" {
  value       = azurerm_resource_group.rg.name
  description = "Name of the resource group"
}

output "virtual_network_id" {
  value       = azurerm_virtual_network.vnet.id
  description = "ID of the virtual network"
}

output "subnet_id" {
  value       = azurerm_subnet.subnet.id
  description = "ID of the subnet"
}

output "public_ip_address" {
  value       = azurerm_public_ip.public_ip.ip_address
  description = "Public IP address of the VM"
}

output "vm_id" {
  value       = azurerm_linux_virtual_machine.vm.id
  description = "ID of the virtual machine"
}

output "vm_name" {
  value       = azurerm_linux_virtual_machine.vm.name
  description = "Name of the virtual machine"
}

output "private_ip_address" {
  value       = azurerm_network_interface.nic.private_ip_address
  description = "Private IP address of the VM"
}

output "vm_fqdn" {
  value       = "VM is ready at: ${azurerm_public_ip.public_ip.ip_address}"
  description = "Connection information for the VM"
}
