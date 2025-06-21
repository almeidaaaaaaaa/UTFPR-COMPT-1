document.addEventListener('DOMContentLoaded', function () {
    // ============ NAVBAR ============
    // Ativar link correspondente à página atual
    function setupNavbarActiveLink() {
        const currentPage = window.location.pathname.split('/').pop() || 'index.html';
        const navLinks = document.querySelectorAll('.nav-link');

        navLinks.forEach(link => {
            const linkHref = link.getAttribute('href');
            if (linkHref === currentPage) {
                link.classList.add('active');
            } else {
                link.classList.remove('active');
            }
        });
    }
    document.getElementById("login-form").addEventListener("submit", async function (e) {
        e.preventDefault(); // Evita envio tradicional do form

        const rg = document.getElementById("login-rg").value;
        const senha = document.getElementById("login-password").value;

        const formData = new URLSearchParams();
        formData.append("login-rg", rg);
        formData.append("login-password", senha);

        const response = await fetch("http://localhost:8080/BonsFluidos/api/login", {
            method: "POST",
            headers: {"Content-Type": "application/x-www-form-urlencoded"},
            body: formData.toString()
        });

        if (response.redirected) {
            window.location.href = response.url; // redirecionamento OK
        } else {
            const text = await response.text();
            alert(text); // mostra "Usuário não existe."
        }
    });

    // ============ GALERIA ============
    // Configurar lightbox para as imagens da galeria
    function setupGalleryLightbox() {
        const galleryItems = document.querySelectorAll('.gallery-item img, .galeria-grid img');

        galleryItems.forEach(img => {
            img.addEventListener('click', function () {
                // Criar lightbox
                const lightbox = document.createElement('div');
                lightbox.id = 'lightbox';
                lightbox.style.position = 'fixed';
                lightbox.style.top = '0';
                lightbox.style.left = '0';
                lightbox.style.width = '100%';
                lightbox.style.height = '100%';
                lightbox.style.backgroundColor = 'rgba(0,0,0,0.8)';
                lightbox.style.display = 'flex';
                lightbox.style.justifyContent = 'center';
                lightbox.style.alignItems = 'center';
                lightbox.style.zIndex = '1000';
                lightbox.style.cursor = 'pointer';

                // Adicionar imagem ao lightbox
                const imgClone = img.cloneNode();
                imgClone.style.maxHeight = '90%';
                imgClone.style.maxWidth = '90%';
                imgClone.style.objectFit = 'contain';

                lightbox.appendChild(imgClone);
                document.body.appendChild(lightbox);

                // Fechar lightbox ao clicar
                lightbox.addEventListener('click', function () {
                    document.body.removeChild(lightbox);
                });
            });
        });
    }

    // ============ FORMULÁRIO DE CONTATO ============
    function setupContactForm() {
        const contactForm = document.querySelector('.contact-form form');
        if (!contactForm)
            return;

        contactForm.addEventListener('submit', function (e) {

            // Simular envio do formulário
            const formData = {
                name: document.getElementById('name').value,
                email: document.getElementById('email').value,
                subject: document.getElementById('subject').value,
                message: document.getElementById('message').value
            };

            console.log('Formulário enviado:', formData);

            // Mostrar mensagem de sucesso
            alert('Mensagem enviada com sucesso! Entraremos em contato em breve.');
            contactForm.reset();
        });
    }

    // ============ PÁGINA DE LOGIN ============
    function setupAuthPage() {
        // Alternar entre login e cadastro
        const tabLogin = document.getElementById('tab-login');
        const tabSignup = document.getElementById('tab-signup');
        const loginPane = document.getElementById('login-pane');
        const signupPane = document.getElementById('signup-pane');

        if (tabLogin && tabSignup) {
            tabLogin.addEventListener('click', function () {
                tabLogin.classList.add('active');
                tabSignup.classList.remove('active');
                loginPane.style.display = 'block';
                signupPane.style.display = 'none';
            });

            tabSignup.addEventListener('click', function () {
                tabSignup.classList.add('active');
                tabLogin.classList.remove('active');
                loginPane.style.display = 'none';
                signupPane.style.display = 'block';
            });
        }

        // Alternar entre tipos de cadastro (voluntário/beneficiado)
        const roleBtns = document.querySelectorAll('.role-btn');
        const formVoluntario = document.getElementById('form-voluntario');
        const formBeneficiado = document.getElementById('form-beneficiado');

        if (roleBtns.length > 0) {
            roleBtns.forEach(btn => {
                btn.addEventListener('click', function () {
                    roleBtns.forEach(b => b.classList.remove('active'));
                    btn.classList.add('active');

                    if (btn.dataset.role === 'voluntario') {
                        formVoluntario.style.display = 'block';
                        formBeneficiado.style.display = 'none';
                    } else {
                        formVoluntario.style.display = 'none';
                        formBeneficiado.style.display = 'block';
                    }
                });
            });
        }

        // Validar formulário de login
        const loginForm = document.getElementById('login-form');
        if (loginForm) {
            loginForm.addEventListener('submit', function (e) {
                const email = document.getElementById('login-email').value;
                const password = document.getElementById('login-password').value;

                // Simular autenticação
                if (email && password) {
                    alert('Login realizado com sucesso! Redirecionando...');
                    // Aqui você pode redirecionar para a página de dashboard
                    // window.location.href = 'dashboard.html';
                } else {
                    alert('Por favor, preencha todos os campos.');
                }
            });
        }
    }

    // ============ INICIALIZAR TODAS AS FUNÇÕES ============
    setupNavbarActiveLink();
    setupGalleryLightbox();
    setupContactForm();
    setupAuthPage();

    // Animação de scroll suave para links internos
    document.querySelectorAll('a[href^="#"]').forEach(anchor => {
        anchor.addEventListener('click', function (e) {
            const targetId = this.getAttribute('href');
            if (targetId === '#')
                return;

            const targetElement = document.querySelector(targetId);
            if (targetElement) {
                targetElement.scrollIntoView({
                    behavior: 'smooth'
                });
            }
        });
    });

    // Efeito de hover nos cards
    document.querySelectorAll('.destaque-card, .membro, .gallery-item').forEach(card => {
        card.addEventListener('mouseenter', function () {
            this.style.transform = 'translateY(-5px)';
            this.style.boxShadow = '0 10px 20px rgba(0,0,0,0.15)';
        });

        card.addEventListener('mouseleave', function () {
            this.style.transform = '';
            this.style.boxShadow = '';
        });
    });
});

// ============ BUSCA DE USUÁRIOS ============
function setupUserSearch() {
    const searchBtn = document.getElementById('search-btn');
    const searchInput = document.getElementById('search-input');
    const userTypeFilter = document.getElementById('user-type-filter');
    const statusFilter = document.getElementById('status-filter');

    if (!searchBtn)
        return;

    // Dados mockados para demonstração
    const mockUsers = [
        {
            id: 1,
            nome: "Maria Silva",
            tipo: "beneficiado",
            email: "maria@email.com",
            telefone: "(43) 99999-9999",
            status: "ativo",
            endereco: "Rua Exemplo, 123"
        },
        {
            id: 2,
            nome: "Joana Oliveira",
            tipo: "voluntario",
            email: "joana@email.com",
            telefone: "(43) 98888-8888",
            status: "ativo",
            formacao: "Estudante de Enfermagem"
        },
        {
            id: 3,
            nome: "Ana Santos",
            tipo: "beneficiado",
            email: "ana@email.com",
            telefone: "(43) 97777-7777",
            status: "inativo",
            endereco: "Av. Principal, 456"
        }
    ];

    function renderUsers(users) {
        const resultsContainer = document.getElementById('users-results');
        const resultsCount = document.getElementById('results-count');

        resultsContainer.innerHTML = '';
        resultsCount.textContent = `${users.length} usuário(s) encontrado(s)`;

        users.forEach(user => {
            const row = document.createElement('tr');

            // Determinar classes para badges
            const tipoClass = user.tipo === 'voluntario' ? 'badge-voluntario' : 'badge-beneficiado';
            const statusClass = user.status === 'ativo' ? 'badge-status' : 'badge-status';

            row.innerHTML = `
        <td>${user.nome}</td>
        <td><span class="user-badge ${tipoClass}">${user.tipo === 'voluntario' ? 'Voluntário' : 'Beneficiado'}</span></td>
        <td>${user.email}</td>
        <td>${user.telefone}</td>
        <td><span class="user-badge ${statusClass}">${user.status === 'ativo' ? 'Ativo' : 'Inativo'}</span></td>
        <td>
          <button class="action-btn" title="Editar">✏️</button>
          <button class="action-btn" title="Visualizar">👁️</button>
          <button class="action-btn" title="${user.status === 'ativo' ? 'Desativar' : 'Ativar'}">${user.status === 'ativo' ? '❌' : '✅'}</button>
        </td>
      `;

            resultsContainer.appendChild(row);
        });
    }

    function filterUsers() {
        const searchTerm = searchInput.value.toLowerCase();
        const typeFilter = userTypeFilter.value;
        const status = statusFilter.value;

        const filtered = mockUsers.filter(user => {
            const matchesSearch = user.nome.toLowerCase().includes(searchTerm) ||
                    user.email.toLowerCase().includes(searchTerm);
            const matchesType = typeFilter === 'all' || user.tipo === typeFilter;
            const matchesStatus = status === 'all' || user.status === status;

            return matchesSearch && matchesType && matchesStatus;
        });

        renderUsers(filtered);
    }

    // Event listeners
    searchBtn.addEventListener('click', filterUsers);
    searchInput.addEventListener('keyup', (e) => {
        if (e.key === 'Enter')
            filterUsers();
    });
    userTypeFilter.addEventListener('change', filterUsers);
    statusFilter.addEventListener('change', filterUsers);

    // Renderizar inicialmente
    renderUsers(mockUsers);
}

// ============ GERENCIAMENTO DE PRODUTOS ============
function setupProductsPage() {
    const addProductBtn = document.getElementById('add-product-btn');
    const productModal = document.getElementById('product-modal');
    const closeModal = document.querySelector('.close-modal');
    const productForm = document.getElementById('product-form');

    if (!addProductBtn)
        return;

    // Dados mockados para demonstração
    const mockProducts = [
        {
            id: 1,
            nome: "Absorvente Higiênico",
            tipo: "absorvente",
            quantidade: 150,
            unidade: "unidade",
            descricao: "Absorvente descartável noturno",
            imagem: "figs/produto1.jpg"
        },
        {
            id: 2,
            nome: "Coletor Menstrual",
            tipo: "coletor",
            quantidade: 30,
            unidade: "unidade",
            descricao: "Coletor reutilizável tamanho P",
            imagem: "figs/produto2.jpg"
        },
        {
            id: 3,
            nome: "Kit Higiene",
            tipo: "outro",
            quantidade: 25,
            unidade: "pacote",
            descricao: "Kit contendo absorventes e lenços umedecidos",
            imagem: "figs/produto3.jpg"
        }
    ];

    function renderProducts(products) {
        const productsGrid = document.querySelector('.products-grid');
        const totalStock = document.getElementById('total-stock');
        const totalDonated = document.getElementById('total-donated');

        productsGrid.innerHTML = '';

        let stockSum = 0;
        let donatedSum = 0; // Simulação - poderia vir dos dados

        products.forEach(product => {
            stockSum += product.quantidade;
            donatedSum += Math.floor(product.quantidade * 0.7); // Simulação de 70% doado

            const productCard = document.createElement('div');
            productCard.className = 'product-card';

            productCard.innerHTML = `
        <img src="${product.imagem || 'figs/produto-padrao.jpg'}" alt="${product.nome}" class="product-image">
        <div class="product-info">
          <h3 class="product-name">${product.nome}</h3>
          <span class="product-type">${getProductTypeName(product.tipo)}</span>
          <p class="product-description">${product.descricao}</p>
          
          <div class="product-stock">
            <span>Estoque:</span>
            <strong>${product.quantidade} ${product.unidade}</strong>
          </div>
          
          <div class="product-actions">
            <button class="action-btn edit-product" data-id="${product.id}">✏️</button>
            <button class="action-btn delete-product" data-id="${product.id}">🗑️</button>
          </div>
        </div>
      `;

            productsGrid.appendChild(productCard);
        });

        totalStock.textContent = stockSum;
        totalDonated.textContent = donatedSum;

        // Adicionar eventos aos botões
        document.querySelectorAll('.edit-product').forEach(btn => {
            btn.addEventListener('click', () => openEditModal(btn.dataset.id));
        });

        document.querySelectorAll('.delete-product').forEach(btn => {
            btn.addEventListener('click', () => confirmDelete(btn.dataset.id));
        });
    }

    function getProductTypeName(type) {
        const types = {
            'absorvente': 'Absorvente',
            'coletor': 'Coletor Menstrual',
            'calcinha': 'Calcinha Absorvente',
            'outro': 'Outro'
        };
        return types[type] || type;
    }

    function openAddModal() {
        document.getElementById('modal-title').textContent = 'Adicionar Novo Produto';
        document.getElementById('product-id').value = '';
        productForm.reset();
        productModal.style.display = 'block';
    }

    function openEditModal(productId) {
        const product = mockProducts.find(p => p.id == productId);
        if (!product)
            return;

        document.getElementById('modal-title').textContent = 'Editar Produto';
        document.getElementById('product-id').value = product.id;
        document.getElementById('product-name').value = product.nome;
        document.getElementById('product-type').value = product.tipo;
        document.getElementById('product-quantity').value = product.quantidade;
        document.getElementById('product-unit').value = product.unidade;
        document.getElementById('product-description').value = product.descricao;

        productModal.style.display = 'block';
    }

    function confirmDelete(productId) {
        if (confirm('Tem certeza que deseja excluir este produto?')) {
            // Simular exclusão
            alert(`Produto ID ${productId} excluído com sucesso!`);
            renderProducts(mockProducts.filter(p => p.id != productId));
        }
    }

    // Event listeners
    addProductBtn.addEventListener('click', openAddModal);

    closeModal.addEventListener('click', () => {
        productModal.style.display = 'none';
    });

    window.addEventListener('click', (e) => {
        if (e.target === productModal) {
            productModal.style.display = 'none';
        }
    });

    productForm.addEventListener('submit', (e) => {

        const productId = document.getElementById('product-id').value;
        const isEdit = !!productId;

        // Simular salvamento
        alert(`Produto ${isEdit ? 'atualizado' : 'adicionado'} com sucesso!`);
        productModal.style.display = 'none';

        // Atualizar lista (em uma aplicação real, isso viria do servidor)
        if (!isEdit) {
            // Adicionar novo produto mock
            const newProduct = {
                id: mockProducts.length + 1,
                nome: document.getElementById('product-name').value,
                tipo: document.getElementById('product-type').value,
                quantidade: parseInt(document.getElementById('product-quantity').value),
                unidade: document.getElementById('product-unit').value,
                descricao: document.getElementById('product-description').value,
                imagem: 'figs/produto-padrao.jpg'
            };
            mockProducts.push(newProduct);
        }

        renderProducts(mockProducts);
    });

    // Renderizar inicialmente
    renderProducts(mockProducts);
}

// Atualize a função de inicialização para incluir as novas páginas
document.addEventListener('DOMContentLoaded', function () {
    // ... (código existente)

    // Adicione estas linhas no final da função
    setupUserSearch();
    setupProductsPage();
});