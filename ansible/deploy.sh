#!/bin/bash
# Script para ejecutar el playbook de Ansible
# Uso: ./deploy.sh [opción]

set -e

# Colores para output
RED='\033[0;31m'
GREEN='\033[0;32m'
YELLOW='\033[1;33m'
BLUE='\033[0;34m'
NC='\033[0m' # No Color

# Variables
SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
PLAYBOOK="$SCRIPT_DIR/playbook.yml"
INVENTORY="$SCRIPT_DIR/inventory.ini"

# Función para imprimir encabezado
print_header() {
    echo -e "${BLUE}================================================${NC}"
    echo -e "${BLUE}$1${NC}"
    echo -e "${BLUE}================================================${NC}"
}

# Función para imprimir éxito
print_success() {
    echo -e "${GREEN}✅ $1${NC}"
}

# Función para imprimir error
print_error() {
    echo -e "${RED}❌ $1${NC}"
}

# Función para imprimir warning
print_warning() {
    echo -e "${YELLOW}⚠️  $1${NC}"
}

# Función para imprimir info
print_info() {
    echo -e "${BLUE}ℹ️  $1${NC}"
}

# Mostrar menú
show_menu() {
    print_header "Opciones de Despliegue"
    echo "1) Ejecutar playbook completo (recomendado)"
    echo "2) Ejecutar con verbose (-v)"
    echo "3) Ejecutar con verbose muy detallado (-vvv)"
    echo "4) Syntax check (verificar sin ejecutar)"
    echo "5) Ejecutar solo task: Instalar Java"
    echo "6) Ejecutar solo task: Copiar JAR"
    echo "7) Ejecutar solo task: Iniciar aplicación"
    echo "8) Mostrar información del inventario"
    echo "9) Salir"
    echo ""
}

# Ejecutar playbook
run_playbook() {
    local args=$1
    
    print_header "Ejecutando Playbook de Despliegue"
    print_info "Playbook: $PLAYBOOK"
    print_info "Inventario: $INVENTORY"
    print_info "Comando: ansible-playbook $args"
    echo ""
    
    cd "$SCRIPT_DIR"
    
    if ansible-playbook $args; then
        print_success "Playbook ejecutado exitosamente"
        return 0
    else
        print_error "Playbook falló"
        return 1
    fi
}

# Verificar requisitos previos
check_prerequisites() {
    print_header "Verificación de Requisitos Previos"
    
    # Verificar Ansible
    if command -v ansible-playbook &> /dev/null; then
        print_success "Ansible está instalado"
    else
        print_error "Ansible no está instalado. Ejecuta: sudo apt install ansible"
        exit 1
    fi
    
    # Verificar inventario
    if [ -f "$INVENTORY" ]; then
        print_success "Inventario encontrado: $INVENTORY"
    else
        print_error "Inventario no encontrado: $INVENTORY"
        exit 1
    fi
    
    # Verificar playbook
    if [ -f "$PLAYBOOK" ]; then
        print_success "Playbook encontrado: $PLAYBOOK"
    else
        print_error "Playbook no encontrado: $PLAYBOOK"
        exit 1
    fi
    
    # Verificar conectividad
    print_info "Verificando conectividad a la VM..."
    if ping -c 1 20.168.76.60 &> /dev/null; then
        print_success "VM es accesible en 20.168.76.60"
    else
        print_warning "No se puede hacer ping a 20.168.76.60"
    fi
    
    echo ""
}

# Mostrar información del inventario
show_inventory() {
    print_header "Información del Inventario"
    ansible-inventory -i "$INVENTORY" --list
    echo ""
}

# Main
main() {
    # Verificar requisitos
    check_prerequisites
    
    # Mostrar menú y obtener opción
    if [ $# -eq 0 ]; then
        # Modo interactivo
        while true; do
            show_menu
            read -p "Selecciona una opción (1-9): " choice
            
            case $choice in
                1)
                    run_playbook "$PLAYBOOK"
                    ;;
                2)
                    run_playbook "$PLAYBOOK -v"
                    ;;
                3)
                    run_playbook "$PLAYBOOK -vvv"
                    ;;
                4)
                    print_header "Verificando Sintaxis"
                    cd "$SCRIPT_DIR"
                    if ansible-playbook "$PLAYBOOK" --syntax-check; then
                        print_success "Sintaxis correcta"
                    else
                        print_error "Errores de sintaxis"
                    fi
                    ;;
                5)
                    run_playbook "$PLAYBOOK --tags 'Instalar OpenJDK 11'"
                    ;;
                6)
                    run_playbook "$PLAYBOOK --tags 'Copiar JAR a la VM'"
                    ;;
                7)
                    run_playbook "$PLAYBOOK --tags 'Iniciar la aplicación Calculadora'"
                    ;;
                8)
                    show_inventory
                    ;;
                9)
                    print_info "Saliendo..."
                    exit 0
                    ;;
                *)
                    print_error "Opción inválida"
                    ;;
            esac
            
            read -p "Presiona Enter para continuar..."
            clear
        done
    else
        # Modo línea de comandos
        case $1 in
            deploy)
                run_playbook "$PLAYBOOK"
                ;;
            deploy-v)
                run_playbook "$PLAYBOOK -v"
                ;;
            check)
                cd "$SCRIPT_DIR"
                ansible-playbook "$PLAYBOOK" --syntax-check
                ;;
            inventory)
                show_inventory
                ;;
            *)
                print_error "Opción desconocida: $1"
                echo "Uso: $0 [deploy|deploy-v|check|inventory]"
                exit 1
                ;;
        esac
    fi
}

# Ejecutar
main "$@"
