document.addEventListener('DOMContentLoaded', function() {
  // Dados dos produtos agora com os novos campos
  let produtos = [
    {
      id: 1,
      nome: "Absorvente Higiênico",
      quantidade: 150,
      destino: "Escola Municipal",
      rg: "12.345.678-9"
    },
    {
      id: 2,
      nome: "Coletor Menstrual",
      quantidade: 30,
      destino: "Centro Comunitário",
      rg: "98.765.432-1"
    }
  ];

  // Elementos do DOM
  const productsGrid = document.querySelector('.products-grid');
  const modal = document.getElementById('product-modal');
  const btnAddProduct = document.getElementById('btn-add-product');
  const closeModal = document.querySelector('.close-modal');
  const productForm = document.getElementById('product-form');
  const modalTitle = document.getElementById('modal-title');

  // Função para renderizar os produtos
  function renderizarProdutos() {
    productsGrid.innerHTML = '';
    
    produtos.forEach(produto => {
      const productCard = document.createElement('div');
      productCard.className = 'product-card';
      productCard.innerHTML = `
        <div class="product-info">
          <h3 class="product-name">${produto.nome}</h3>
          <p class="product-description">
            <strong>Quantidade:</strong> ${produto.quantidade}<br>
            <strong>Destino:</strong> ${produto.destino}<br>
            <strong>RG:</strong> ${produto.rg}
          </p>
          <div class="product-actions-admin">
            <button class="btn btn-edit" data-id="${produto.id}">Editar</button>
            <button class="btn btn-delete" data-id="${produto.id}">Excluir</button>
          </div>
        </div>
      `;
      productsGrid.appendChild(productCard);
    });


    // Adiciona eventos aos botões de editar e excluir
    document.querySelectorAll('.btn-edit').forEach(btn => {
      btn.addEventListener('click', editarProduto);
    });
    
    document.querySelectorAll('.btn-delete').forEach(btn => {
      btn.addEventListener('click', excluirProduto);
    });
  }

  // Função auxiliar para obter o nome do tipo de produto
  function getProductTypeName(type) {
    const types = {
      'absorvente': 'Absorvente',
      'coletor': 'Coletor Menstrual',
      'calcinha': 'Calcinha Absorvente',
      'outro': 'Outro'
    };
    return types[type] || type;
  }

  // Função para editar produto
  function editarProduto(e) {
    const id = parseInt(e.target.dataset.id);
    const produto = produtos.find(p => p.id === id);
    
    if (produto) {
      modalTitle.textContent = 'Editar Produto';
      document.getElementById('product-id').value = produto.id;
      document.getElementById('product-name').value = produto.nome;
      document.getElementById('product-quantity').value = produto.quantidade;
      document.getElementById('product-destination').value = produto.destino;
      document.getElementById('product-rg').value = produto.rg;
      
      modal.style.display = 'block';
    }
  }

  // Função para excluir produto
  function excluirProduto(e) {
    const id = parseInt(e.target.dataset.id);
    if (confirm('Tem certeza que deseja excluir este produto?')) {
      produtos = produtos.filter(p => p.id !== id);
      renderizarProdutos();
    }
  }

  // Evento para abrir modal de adição
  btnAddProduct.addEventListener('click', function() {
    modalTitle.textContent = 'Adicionar Produto';
    productForm.reset();
    document.getElementById('product-id').value = '';
    modal.style.display = 'block';
  });

  // Evento para fechar modal
  closeModal.addEventListener('click', function() {
    modal.style.display = 'none';
  });

  // Evento para fechar modal ao clicar fora
  window.addEventListener('click', function(event) {
    if (event.target === modal) {
      modal.style.display = 'none';
    }
  });

  // Evento de submit do formulário
   productForm.addEventListener('submit', function(e) {
    e.preventDefault();
    
    const id = document.getElementById('product-id').value;
    const produto = {
      nome: document.getElementById('product-name').value,
      quantidade: parseInt(document.getElementById('product-quantity').value),
      destino: document.getElementById('product-destination').value,
      rg: document.getElementById('product-rg').value
    };
    
    if (id) {
      // Editar produto existente
      const index = produtos.findIndex(p => p.id === parseInt(id));
      if (index !== -1) {
        produtos[index] = { ...produtos[index], ...produto };
      }
    } else {
      // Adicionar novo produto
      produto.id = produtos.length > 0 ? Math.max(...produtos.map(p => p.id)) + 1 : 1;
      produtos.push(produto);
    }
    
    modal.style.display = 'none';
    renderizarProdutos();
    productForm.reset();
  });

  // Renderiza os produtos inicialmente
  renderizarProdutos();
});