namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    partial class FlightResultForm
    {
        /// <summary>
        /// Required designer variable.
        /// </summary>
        private System.ComponentModel.IContainer components = null;

        /// <summary>
        /// Clean up any resources being used.
        /// </summary>
        /// <param name="disposing">true if managed resources should be disposed; otherwise, false.</param>
        protected override void Dispose(bool disposing)
        {
            if (disposing && (components != null))
            {
                components.Dispose();
            }
            base.Dispose(disposing);
        }

        #region Windows Form Designer generated code

        /// <summary>
        /// Required method for Designer support - do not modify
        /// the contents of this method with the code editor.
        /// </summary>
        private void InitializeComponent()
        {
            headerPanel = new Panel();
            lblSubtitle = new Label();
            lblTitle = new Label();
            contentPanel = new Panel();
            resultPanel = new Panel();
            linkPanel = new Panel();
            btnBack = new Button();
            lblLogin = new Label();
            btnComprar = new Button();
            lblHora = new Label();
            lblValor = new Label();
            lblDestino = new Label();
            lblOrigen = new Label();
            lblId = new Label();
            lblFormTitle = new Label();
            headerPanel.SuspendLayout();
            contentPanel.SuspendLayout();
            resultPanel.SuspendLayout();
            linkPanel.SuspendLayout();
            SuspendLayout();
            // 
            // headerPanel
            // 
            headerPanel.BackColor = Color.FromArgb(163, 191, 250);
            headerPanel.Controls.Add(lblSubtitle);
            headerPanel.Controls.Add(lblTitle);
            headerPanel.Dock = DockStyle.Top;
            headerPanel.Location = new Point(0, 0);
            headerPanel.Margin = new Padding(4, 5, 4, 5);
            headerPanel.Name = "headerPanel";
            headerPanel.Size = new Size(1067, 185);
            headerPanel.TabIndex = 0;
            // 
            // lblSubtitle
            // 
            lblSubtitle.AutoSize = true;
            lblSubtitle.Font = new Font("Microsoft Sans Serif", 12F);
            lblSubtitle.ForeColor = Color.White;
            lblSubtitle.Location = new Point(467, 92);
            lblSubtitle.Margin = new Padding(4, 0, 4, 0);
            lblSubtitle.Name = "lblSubtitle";
            lblSubtitle.Size = new Size(564, 25);
            lblSubtitle.TabIndex = 1;
            lblSubtitle.Text = "Encuentra y compra tus boletos de avión de forma fácil y segura";
            // 
            // lblTitle
            // 
            lblTitle.AutoSize = true;
            lblTitle.Font = new Font("Microsoft Sans Serif", 22F, FontStyle.Bold);
            lblTitle.ForeColor = Color.White;
            lblTitle.Location = new Point(467, 31);
            lblTitle.Margin = new Padding(4, 0, 4, 0);
            lblTitle.Name = "lblTitle";
            lblTitle.Size = new Size(251, 42);
            lblTitle.TabIndex = 0;
            lblTitle.Text = "Viajecitos SA";
            // 
            // contentPanel
            // 
            contentPanel.BackColor = Color.FromArgb(241, 245, 249);
            contentPanel.Controls.Add(resultPanel);
            contentPanel.Dock = DockStyle.Fill;
            contentPanel.Location = new Point(0, 185);
            contentPanel.Margin = new Padding(4, 5, 4, 5);
            contentPanel.Name = "contentPanel";
            contentPanel.Size = new Size(1067, 661);
            contentPanel.TabIndex = 1;
            // 
            // resultPanel
            // 
            resultPanel.BackColor = Color.White;
            resultPanel.BorderStyle = BorderStyle.FixedSingle;
            resultPanel.Controls.Add(linkPanel);
            resultPanel.Controls.Add(lblLogin);
            resultPanel.Controls.Add(btnComprar);
            resultPanel.Controls.Add(lblHora);
            resultPanel.Controls.Add(lblValor);
            resultPanel.Controls.Add(lblDestino);
            resultPanel.Controls.Add(lblOrigen);
            resultPanel.Controls.Add(lblId);
            resultPanel.Controls.Add(lblFormTitle);
            resultPanel.Location = new Point(133, 77);
            resultPanel.Margin = new Padding(4, 5, 4, 5);
            resultPanel.Name = "resultPanel";
            resultPanel.Size = new Size(799, 614);
            resultPanel.TabIndex = 0;
            // 
            // linkPanel
            // 
            linkPanel.Controls.Add(btnBack);
            linkPanel.Location = new Point(0, 462);
            linkPanel.Margin = new Padding(4, 5, 4, 5);
            linkPanel.Name = "linkPanel";
            linkPanel.Size = new Size(800, 77);
            linkPanel.TabIndex = 8;
            // 
            // btnBack
            // 
            btnBack.AutoSize = true;
            btnBack.FlatAppearance.BorderSize = 0;
            btnBack.FlatStyle = FlatStyle.Flat;
            btnBack.Font = new Font("Microsoft Sans Serif", 9F);
            btnBack.ForeColor = Color.FromArgb(59, 130, 246);
            btnBack.Location = new Point(333, 15);
            btnBack.Margin = new Padding(4, 5, 4, 5);
            btnBack.Name = "btnBack";
            btnBack.Size = new Size(163, 46);
            btnBack.TabIndex = 1;
            btnBack.Text = "Volver a Buscar";
            btnBack.UseVisualStyleBackColor = true;
            // 
            // lblLogin
            // 
            lblLogin.AutoSize = true;
            lblLogin.Font = new Font("Microsoft Sans Serif", 9F);
            lblLogin.ForeColor = Color.FromArgb(220, 38, 38);
            lblLogin.Location = new Point(293, 338);
            lblLogin.Margin = new Padding(4, 0, 4, 0);
            lblLogin.Name = "lblLogin";
            lblLogin.Size = new Size(182, 18);
            lblLogin.TabIndex = 7;
            lblLogin.Text = "Inicie sesión para comprar";
            lblLogin.Visible = false;
            // 
            // btnComprar
            // 
            btnComprar.BackColor = Color.FromArgb(191, 219, 254);
            btnComprar.FlatStyle = FlatStyle.Flat;
            btnComprar.Font = new Font("Microsoft Sans Serif", 10F, FontStyle.Bold);
            btnComprar.ForeColor = Color.Black;
            btnComprar.Location = new Point(300, 338);
            btnComprar.Margin = new Padding(4, 5, 4, 5);
            btnComprar.Name = "btnComprar";
            btnComprar.Size = new Size(200, 62);
            btnComprar.TabIndex = 6;
            btnComprar.Text = "Comprar Boleto";
            btnComprar.UseVisualStyleBackColor = false;
            btnComprar.Visible = false;
            // 
            // lblHora
            // 
            lblHora.AutoSize = true;
            lblHora.Font = new Font("Microsoft Sans Serif", 10F);
            lblHora.ForeColor = Color.FromArgb(31, 41, 55);
            lblHora.Location = new Point(67, 277);
            lblHora.Margin = new Padding(4, 0, 4, 0);
            lblHora.Name = "lblHora";
            lblHora.Size = new Size(125, 20);
            lblHora.TabIndex = 5;
            lblHora.Text = "Hora de Salida:";
            // 
            // lblValor
            // 
            lblValor.AutoSize = true;
            lblValor.Font = new Font("Microsoft Sans Serif", 10F);
            lblValor.ForeColor = Color.FromArgb(31, 41, 55);
            lblValor.Location = new Point(67, 231);
            lblValor.Margin = new Padding(4, 0, 4, 0);
            lblValor.Name = "lblValor";
            lblValor.Size = new Size(53, 20);
            lblValor.TabIndex = 4;
            lblValor.Text = "Valor:";
            // 
            // lblDestino
            // 
            lblDestino.AutoSize = true;
            lblDestino.Font = new Font("Microsoft Sans Serif", 10F);
            lblDestino.ForeColor = Color.FromArgb(31, 41, 55);
            lblDestino.Location = new Point(67, 185);
            lblDestino.Margin = new Padding(4, 0, 4, 0);
            lblDestino.Name = "lblDestino";
            lblDestino.Size = new Size(72, 20);
            lblDestino.TabIndex = 3;
            lblDestino.Text = "Destino:";
            // 
            // lblOrigen
            // 
            lblOrigen.AutoSize = true;
            lblOrigen.Font = new Font("Microsoft Sans Serif", 10F);
            lblOrigen.ForeColor = Color.FromArgb(31, 41, 55);
            lblOrigen.Location = new Point(67, 138);
            lblOrigen.Margin = new Padding(4, 0, 4, 0);
            lblOrigen.Name = "lblOrigen";
            lblOrigen.Size = new Size(64, 20);
            lblOrigen.TabIndex = 2;
            lblOrigen.Text = "Origen:";
            // 
            // lblId
            // 
            lblId.AutoSize = true;
            lblId.Font = new Font("Microsoft Sans Serif", 10F);
            lblId.ForeColor = Color.FromArgb(31, 41, 55);
            lblId.Location = new Point(67, 92);
            lblId.Margin = new Padding(4, 0, 4, 0);
            lblId.Name = "lblId";
            lblId.Size = new Size(78, 20);
            lblId.TabIndex = 1;
            lblId.Text = "Vuelo ID:";
            // 
            // lblFormTitle
            // 
            lblFormTitle.AutoSize = true;
            lblFormTitle.Font = new Font("Microsoft Sans Serif", 16F, FontStyle.Bold);
            lblFormTitle.ForeColor = Color.FromArgb(31, 41, 55);
            lblFormTitle.Location = new Point(240, 31);
            lblFormTitle.Margin = new Padding(4, 0, 4, 0);
            lblFormTitle.Name = "lblFormTitle";
            lblFormTitle.Size = new Size(355, 31);
            lblFormTitle.TabIndex = 0;
            lblFormTitle.Text = "Resultado de la Búsqueda";
            // 
            // FlightResultForm
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.FromArgb(241, 245, 249);
            ClientSize = new Size(1067, 846);
            Controls.Add(contentPanel);
            Controls.Add(headerPanel);
            Margin = new Padding(4, 5, 4, 5);
            Name = "FlightResultForm";
            Text = "Viajecitos SA - Resultado";
            WindowState = FormWindowState.Maximized;
            headerPanel.ResumeLayout(false);
            headerPanel.PerformLayout();
            contentPanel.ResumeLayout(false);
            resultPanel.ResumeLayout(false);
            resultPanel.PerformLayout();
            linkPanel.ResumeLayout(false);
            linkPanel.PerformLayout();
            ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel headerPanel;
        private System.Windows.Forms.Label lblSubtitle;
        private System.Windows.Forms.Label lblTitle;
        private System.Windows.Forms.Panel contentPanel;
        private System.Windows.Forms.Panel resultPanel;
        private System.Windows.Forms.Panel linkPanel;
        private System.Windows.Forms.Button btnBack;
        private System.Windows.Forms.Label lblLogin;
        private System.Windows.Forms.Button btnComprar;
        private System.Windows.Forms.Label lblHora;
        private System.Windows.Forms.Label lblValor;
        private System.Windows.Forms.Label lblDestino;
        private System.Windows.Forms.Label lblOrigen;
        private System.Windows.Forms.Label lblId;
        private System.Windows.Forms.Label lblFormTitle;
    }
}