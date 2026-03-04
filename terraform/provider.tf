terraform {
  required_providers {
    azurerm = {
      source  = "hashicorp/azurerm"
      version = "~> 3.0"
    }
  }
  required_version = ">= 1.0"      #version de Terraform
}

#Las credenciales se cargan automaticamente desde el CLI, Terraform encuentra los tokens en  ~/.azure/
provider "azurerm" {
  features {}

  # Las credenciales se cargan automáticamente desde:
  # - Variables de entorno: ARM_SUBSCRIPTION_ID, ARM_CLIENT_ID, ARM_CLIENT_SECRET, ARM_TENANT_ID
  # - O desde Azure CLI tokens (~/.azure/) si ya hiciste 'az login'
  # NO incluyas credenciales explícitamente aquí (INSEGURO)
}
