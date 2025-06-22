document.addEventListener('DOMContentLoaded', function () {
    let produtos = [];

    const productsGrid = document.querySelector('.products-grid');
    const modal = document.getElementById('product-modal');
    const btnAddProduct = document.getElementById('btn-add-product');
    const closeModal = document.querySelector('.close-modal');
    const productForm = document.getElementById('product-form');
    const modalTitle = document.getElementById('modal-title');

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
                        <strong>RG:</strong> ${produto.rg}
                    </p>
                    <div class="product-actions-admin">
                        <button class="btn btn-delete" data-id="${produto.id}">Excluir</button>
                    </div>
                </div>
            `;
            productsGrid.appendChild(productCard);
        });

        document.querySelectorAll('.btn-delete').forEach(btn => {
            btn.addEventListener('click', excluirProduto);
        });
    }

    function excluirProduto(e) {
        const id = parseInt(e.target.dataset.id);
        if (confirm('Tem certeza que deseja excluir este produto?')) {
            fetch(`http://localhost:8080/BonsFluidos/api/produto?id=${id}`, {
                method: 'DELETE'
            })
                    .then(response => {
                        if (response.ok) {
                            produtos = produtos.filter(p => p.id !== id);
                            renderizarProdutos();
                        } else {
                            console.error("Erro ao excluir produto do backend");
                        }
                    })
                    .catch(error => {
                        console.error("Erro na requisição DELETE:", error.message);
                    });
        }
    }

    btnAddProduct.addEventListener('click', function () {
        modalTitle.textContent = 'Adicionar Produto';
        productForm.reset();
        document.getElementById('product-id').value = '';
        modal.style.display = 'block';
    });

    closeModal.addEventListener('click', function () {
        modal.style.display = 'none';
    });

    window.addEventListener('click', function (event) {
        if (event.target === modal) {
            modal.style.display = 'none';
        }
    });

    productForm.addEventListener('submit', function (e) {
        e.preventDefault();

        const id = document.getElementById('product-id').value;
        const produto = {
            nome: document.getElementById('product-name').value,
            quantidade: parseInt(document.getElementById('product-quantity').value),
            rg: document.getElementById('product-rg').value
        };

        if (id) {
            // Atualização local, se necessário implementar PUT depois
            const index = produtos.findIndex(p => p.id === parseInt(id));
            if (index !== -1) {
                produtos[index] = {...produtos[index], ...produto};
                renderizarProdutos();
            }
            modal.style.display = 'none';
            productForm.reset();
            return;
        }

        // Enviar novo produto para o backend
        fetch('http://localhost:8080/BonsFluidos/api/produto', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: new URLSearchParams({
                nome: produto.nome,
                quantidade: produto.quantidade,
                rg: produto.rg
            })
        })
                .then(response => {
                    if (!response.ok) {
                        return response.text().then(text => {
                            throw new Error(text);
                        });
                    }
                    return response.json(); // backend deve retornar o estoque com ID
                })
                .then(data => {
                    produto.id = data.codigo;
                    produtos.push(produto);
                    renderizarProdutos();
                })
                .catch(error => {
                    console.error('Erro ao salvar no backend:', error.message);
                });

        modal.style.display = 'none';
        productForm.reset();
    });

    // Carrega produtos do backend ao iniciar
    fetch('http://localhost:8080/BonsFluidos/api/produto')
            .then(response => response.json())
            .then(data => {
                produtos = data.map(p => ({
                        id: p.codigo,
                        nome: p.est_nome,
                        quantidade: p.quantidade,
                        rg: p.rg
                    }));
                renderizarProdutos();
            })
            .catch(error => {
                console.error("Erro ao buscar produtos do backend:", error);
                alert('Erro ao cadastrar produto: ' + error.message);
            });
});
