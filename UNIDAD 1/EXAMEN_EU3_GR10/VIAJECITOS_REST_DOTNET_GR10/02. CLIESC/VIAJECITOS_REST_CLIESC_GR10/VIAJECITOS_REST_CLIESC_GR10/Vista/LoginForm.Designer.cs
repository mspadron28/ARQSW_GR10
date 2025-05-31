namespace VIAJECITOS_REST_CLIESC_GR10.Vista
{
    partial class LoginForm
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
            formPanel = new Panel();
            linkPanel = new Panel();
            btnBack = new Button();
            btnRegister = new Button();
            lblError = new Label();
            btnLogin = new Button();
            txtClave = new TextBox();
            lblClave = new Label();
            txtUsuario = new TextBox();
            lblUsuario = new Label();
            lblFormTitle = new Label();
            headerPanel.SuspendLayout();
            contentPanel.SuspendLayout();
            formPanel.SuspendLayout();
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
            contentPanel.Controls.Add(formPanel);
            contentPanel.Dock = DockStyle.Fill;
            contentPanel.Location = new Point(0, 185);
            contentPanel.Margin = new Padding(4, 5, 4, 5);
            contentPanel.Name = "contentPanel";
            contentPanel.Size = new Size(1067, 661);
            contentPanel.TabIndex = 1;
            // 
            // formPanel
            // 
            formPanel.BackColor = Color.White;
            formPanel.BorderStyle = BorderStyle.FixedSingle;
            formPanel.Controls.Add(linkPanel);
            formPanel.Controls.Add(lblError);
            formPanel.Controls.Add(btnLogin);
            formPanel.Controls.Add(txtClave);
            formPanel.Controls.Add(lblClave);
            formPanel.Controls.Add(txtUsuario);
            formPanel.Controls.Add(lblUsuario);
            formPanel.Controls.Add(lblFormTitle);
            formPanel.Location = new Point(133, 77);
            formPanel.Margin = new Padding(4, 5, 4, 5);
            formPanel.Name = "formPanel";
            formPanel.Size = new Size(799, 614);
            formPanel.TabIndex = 0;
            // 
            // linkPanel
            // 
            linkPanel.Controls.Add(btnBack);
            linkPanel.Controls.Add(btnRegister);
            linkPanel.Location = new Point(0, 462);
            linkPanel.Margin = new Padding(4, 5, 4, 5);
            linkPanel.Name = "linkPanel";
            linkPanel.Size = new Size(800, 77);
            linkPanel.TabIndex = 7;
            // 
            // btnBack
            // 
            btnBack.AutoSize = true;
            btnBack.FlatAppearance.BorderSize = 0;
            btnBack.FlatStyle = FlatStyle.Flat;
            btnBack.Font = new Font("Microsoft Sans Serif", 9F);
            btnBack.ForeColor = Color.FromArgb(59, 130, 246);
            btnBack.Location = new Point(467, 15);
            btnBack.Margin = new Padding(4, 5, 4, 5);
            btnBack.Name = "btnBack";
            btnBack.Size = new Size(80, 46);
            btnBack.TabIndex = 1;
            btnBack.Text = "Volver";
            btnBack.UseVisualStyleBackColor = true;
            // 
            // btnRegister
            // 
            btnRegister.AutoSize = true;
            btnRegister.FlatAppearance.BorderSize = 0;
            btnRegister.FlatStyle = FlatStyle.Flat;
            btnRegister.Font = new Font("Microsoft Sans Serif", 9F);
            btnRegister.ForeColor = Color.FromArgb(59, 130, 246);
            btnRegister.Location = new Point(267, 15);
            btnRegister.Margin = new Padding(4, 5, 4, 5);
            btnRegister.Name = "btnRegister";
            btnRegister.Size = new Size(125, 46);
            btnRegister.TabIndex = 0;
            btnRegister.Text = "Registrarse";
            btnRegister.UseVisualStyleBackColor = true;
            // 
            // lblError
            // 
            lblError.AutoSize = true;
            lblError.Font = new Font("Microsoft Sans Serif", 9F);
            lblError.ForeColor = Color.FromArgb(220, 38, 38);
            lblError.Location = new Point(67, 385);
            lblError.Margin = new Padding(4, 0, 4, 0);
            lblError.Name = "lblError";
            lblError.Size = new Size(0, 18);
            lblError.TabIndex = 6;
            lblError.TextAlign = ContentAlignment.MiddleCenter;
            // 
            // btnLogin
            // 
            btnLogin.BackColor = Color.FromArgb(191, 219, 254);
            btnLogin.FlatStyle = FlatStyle.Flat;
            btnLogin.Font = new Font("Microsoft Sans Serif", 10F, FontStyle.Bold);
            btnLogin.ForeColor = Color.Black;
            btnLogin.Location = new Point(300, 308);
            btnLogin.Margin = new Padding(4, 5, 4, 5);
            btnLogin.Name = "btnLogin";
            btnLogin.Size = new Size(200, 62);
            btnLogin.TabIndex = 5;
            btnLogin.Text = "Iniciar Sesión";
            btnLogin.UseVisualStyleBackColor = false;
            // 
            // txtClave
            // 
            txtClave.BackColor = Color.FromArgb(243, 244, 246);
            txtClave.Font = new Font("Microsoft Sans Serif", 10F);
            txtClave.ForeColor = Color.FromArgb(31, 41, 55);
            txtClave.Location = new Point(67, 246);
            txtClave.Margin = new Padding(4, 5, 4, 5);
            txtClave.Name = "txtClave";
            txtClave.PasswordChar = '*';
            txtClave.Size = new Size(665, 26);
            txtClave.TabIndex = 4;
            // 
            // lblClave
            // 
            lblClave.AutoSize = true;
            lblClave.Font = new Font("Microsoft Sans Serif", 10F);
            lblClave.ForeColor = Color.FromArgb(31, 41, 55);
            lblClave.Location = new Point(67, 200);
            lblClave.Margin = new Padding(4, 0, 4, 0);
            lblClave.Name = "lblClave";
            lblClave.Size = new Size(100, 20);
            lblClave.TabIndex = 3;
            lblClave.Text = "Contraseña:";
            // 
            // txtUsuario
            // 
            txtUsuario.BackColor = Color.FromArgb(243, 244, 246);
            txtUsuario.Font = new Font("Microsoft Sans Serif", 10F);
            txtUsuario.ForeColor = Color.FromArgb(31, 41, 55);
            txtUsuario.Location = new Point(67, 138);
            txtUsuario.Margin = new Padding(4, 5, 4, 5);
            txtUsuario.Name = "txtUsuario";
            txtUsuario.Size = new Size(665, 26);
            txtUsuario.TabIndex = 2;
            // 
            // lblUsuario
            // 
            lblUsuario.AutoSize = true;
            lblUsuario.Font = new Font("Microsoft Sans Serif", 10F);
            lblUsuario.ForeColor = Color.FromArgb(31, 41, 55);
            lblUsuario.Location = new Point(67, 92);
            lblUsuario.Margin = new Padding(4, 0, 4, 0);
            lblUsuario.Name = "lblUsuario";
            lblUsuario.Size = new Size(72, 20);
            lblUsuario.TabIndex = 1;
            lblUsuario.Text = "Usuario:";
            // 
            // lblFormTitle
            // 
            lblFormTitle.AutoSize = true;
            lblFormTitle.Font = new Font("Microsoft Sans Serif", 16F, FontStyle.Bold);
            lblFormTitle.ForeColor = Color.FromArgb(31, 41, 55);
            lblFormTitle.Location = new Point(293, 31);
            lblFormTitle.Margin = new Padding(4, 0, 4, 0);
            lblFormTitle.Name = "lblFormTitle";
            lblFormTitle.Size = new Size(191, 31);
            lblFormTitle.TabIndex = 0;
            lblFormTitle.Text = "Iniciar Sesión";
            // 
            // LoginForm
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.FromArgb(241, 245, 249);
            ClientSize = new Size(1067, 846);
            Controls.Add(contentPanel);
            Controls.Add(headerPanel);
            Margin = new Padding(4, 5, 4, 5);
            Name = "LoginForm";
            Text = "Viajecitos SA - Iniciar Sesión";
            headerPanel.ResumeLayout(false);
            headerPanel.PerformLayout();
            contentPanel.ResumeLayout(false);
            formPanel.ResumeLayout(false);
            formPanel.PerformLayout();
            linkPanel.ResumeLayout(false);
            linkPanel.PerformLayout();
            ResumeLayout(false);

        }

        #endregion

        private System.Windows.Forms.Panel headerPanel;
        private System.Windows.Forms.Label lblSubtitle;
        private System.Windows.Forms.Label lblTitle;
        private System.Windows.Forms.Panel contentPanel;
        private System.Windows.Forms.Panel formPanel;
        private System.Windows.Forms.Panel linkPanel;
        private System.Windows.Forms.Button btnBack;
        private System.Windows.Forms.Button btnRegister;
        private System.Windows.Forms.Label lblError;
        private System.Windows.Forms.Button btnLogin;
        private System.Windows.Forms.TextBox txtClave;
        private System.Windows.Forms.Label lblClave;
        private System.Windows.Forms.TextBox txtUsuario;
        private System.Windows.Forms.Label lblUsuario;
        private System.Windows.Forms.Label lblFormTitle;
    }
}