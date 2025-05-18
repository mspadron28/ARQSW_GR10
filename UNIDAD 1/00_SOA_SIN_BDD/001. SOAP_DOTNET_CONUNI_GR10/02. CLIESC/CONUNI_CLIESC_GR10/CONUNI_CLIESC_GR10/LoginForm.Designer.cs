namespace CONUNI_CLIESC_GR10
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
            this.components = new System.ComponentModel.Container();
            System.ComponentModel.ComponentResourceManager resources = new System.ComponentModel.ComponentResourceManager(typeof(LoginForm));
            this.headerPanel = new System.Windows.Forms.Panel();
            this.headerLabel = new System.Windows.Forms.Label();
            this.containerPanel = new System.Windows.Forms.Panel();
            this.mainPanel = new System.Windows.Forms.Panel();
            this.layoutPanel = new System.Windows.Forms.TableLayoutPanel();
            this.welcomeLabel = new System.Windows.Forms.Label();
            this.subtextLabel = new System.Windows.Forms.Label();
            this.avatarLabel = new System.Windows.Forms.PictureBox();
            this.userLabel = new System.Windows.Forms.Label();
            this.txtUsuario = new System.Windows.Forms.TextBox();
            this.passwordLabel = new System.Windows.Forms.Label();
            this.txtContraseña = new System.Windows.Forms.TextBox();
            this.btnIniciarSesión = new System.Windows.Forms.Button();
            this.headerPanel.SuspendLayout();
            this.containerPanel.SuspendLayout();
            this.mainPanel.SuspendLayout();
            this.layoutPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.avatarLabel)).BeginInit();
            this.SuspendLayout();
            // 
            // headerPanel
            // 
            this.headerPanel.BackColor = System.Drawing.Color.FromArgb(70, 83, 93);
            this.headerPanel.Controls.Add(this.headerLabel);
            this.headerPanel.Dock = System.Windows.Forms.DockStyle.Top;
            this.headerPanel.Location = new System.Drawing.Point(0, 0);
            this.headerPanel.Name = "headerPanel";
            this.headerPanel.Size = new System.Drawing.Size(1366, 60);
            this.headerPanel.TabIndex = 0;
            // 
            // headerLabel
            // 
            this.headerLabel.AutoSize = true;
            this.headerLabel.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.headerLabel.ForeColor = System.Drawing.Color.White;
            this.headerLabel.Location = new System.Drawing.Point(20, 15);
            this.headerLabel.Name = "headerLabel";
            this.headerLabel.Size = new System.Drawing.Size(229, 32);
            this.headerLabel.TabIndex = 0;
            this.headerLabel.Text = "Inicio de sesión";
            // 
            // containerPanel
            // 
            this.containerPanel.Controls.Add(this.mainPanel);
            this.containerPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.containerPanel.Location = new System.Drawing.Point(0, 60);
            this.containerPanel.Name = "containerPanel";
            this.containerPanel.Padding = new System.Windows.Forms.Padding(20);
            this.containerPanel.Size = new System.Drawing.Size(1366, 708);
            this.containerPanel.TabIndex = 1;
            // 
            // mainPanel
            // 
            this.mainPanel.BackColor = System.Drawing.Color.FromArgb(246, 247, 246);
            this.mainPanel.Controls.Add(this.layoutPanel);
            this.mainPanel.Location = new System.Drawing.Point(433, 54); // Centrado: (1366 - 500) / 2 = 433, margen superior
            this.mainPanel.Name = "mainPanel";
            this.mainPanel.Size = new System.Drawing.Size(500, 600);
            this.mainPanel.TabIndex = 0;
            this.mainPanel.Anchor = AnchorStyles.None; // Centrado en el contenedor
                                                       // 
                                                       // layoutPanel
                                                       // 
            this.layoutPanel.ColumnCount = 1;
            this.layoutPanel.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.layoutPanel.Controls.Add(this.welcomeLabel, 0, 0);
            this.layoutPanel.Controls.Add(this.subtextLabel, 0, 1);
            this.layoutPanel.Controls.Add(this.avatarLabel, 0, 2);
            this.layoutPanel.Controls.Add(this.userLabel, 0, 3);
            this.layoutPanel.Controls.Add(this.txtUsuario, 0, 4);
            this.layoutPanel.Controls.Add(this.passwordLabel, 0, 5);
            this.layoutPanel.Controls.Add(this.txtContraseña, 0, 6);
            this.layoutPanel.Controls.Add(this.btnIniciarSesión, 0, 7);
            this.layoutPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.layoutPanel.Location = new System.Drawing.Point(0, 0);
            this.layoutPanel.Name = "layoutPanel";
            this.layoutPanel.RowCount = 8;
            this.layoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 80F));
            this.layoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 50F));
            this.layoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 180F));
            this.layoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 50F));
            this.layoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 50F));
            this.layoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 50F));
            this.layoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 50F));
            this.layoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 80F));
            this.layoutPanel.Size = new System.Drawing.Size(500, 600);
            this.layoutPanel.TabIndex = 0;
            // 
            // welcomeLabel
            // 
            this.welcomeLabel.AutoSize = true;
            this.welcomeLabel.Font = new System.Drawing.Font("Segoe UI", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.welcomeLabel.Location = new System.Drawing.Point(0, 20);
            this.welcomeLabel.Name = "welcomeLabel";
            this.welcomeLabel.Size = new System.Drawing.Size(500, 45);
            this.welcomeLabel.TabIndex = 0;
            this.welcomeLabel.Text = "Bienvenido";
            this.welcomeLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.welcomeLabel.Anchor = AnchorStyles.None;
            // 
            // subtextLabel
            // 
            this.subtextLabel.AutoSize = true;
            this.subtextLabel.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.subtextLabel.ForeColor = System.Drawing.Color.FromArgb(102, 102, 102);
            this.subtextLabel.Location = new System.Drawing.Point(0, 80);
            this.subtextLabel.Name = "subtextLabel";
            this.subtextLabel.Size = new System.Drawing.Size(500, 25);
            this.subtextLabel.TabIndex = 1;
            this.subtextLabel.Text = "Por favor, ingresa tus credenciales";
            this.subtextLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.subtextLabel.Anchor = AnchorStyles.None;
            // 
            // avatarLabel
            // 
            this.avatarLabel.Location = new System.Drawing.Point(175, 130);
            this.avatarLabel.Name = "avatarLabel";
            this.avatarLabel.Size = new System.Drawing.Size(150, 150);
            this.avatarLabel.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.avatarLabel.TabIndex = 2;
            this.avatarLabel.TabStop = false;
            this.avatarLabel.Image = System.Drawing.Image.FromFile(Path.Combine(Application.StartupPath, "Images", "sulley.png"));
            this.avatarLabel.Anchor = AnchorStyles.None;
            // 
            // userLabel
            // 
            this.userLabel.AutoSize = true;
            this.userLabel.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.userLabel.Location = new System.Drawing.Point(50, 310);
            this.userLabel.Name = "userLabel";
            this.userLabel.Size = new System.Drawing.Size(66, 25);
            this.userLabel.TabIndex = 3;
            this.userLabel.Text = "Usuario";
            this.userLabel.Anchor = AnchorStyles.None;
            // txtUsuario
            this.txtUsuario.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtUsuario.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtUsuario.Location = new System.Drawing.Point(100, 340);
            this.txtUsuario.Name = "txtUsuario";
            this.txtUsuario.Size = new System.Drawing.Size(300, 40);
            this.txtUsuario.TabIndex = 4;
            this.txtUsuario.Anchor = AnchorStyles.None;
            // 
            // passwordLabel
            this.passwordLabel.AutoSize = true;
            this.passwordLabel.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.passwordLabel.Location = new System.Drawing.Point(50, 390);
            this.passwordLabel.Name = "passwordLabel";
            this.passwordLabel.Size = new System.Drawing.Size(95, 25);
            this.passwordLabel.TabIndex = 5;
            this.passwordLabel.Text = "Contraseña";
            this.passwordLabel.Anchor = AnchorStyles.None;
            // 
            // txtContraseña
            this.txtContraseña.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.txtContraseña.Font = new System.Drawing.Font("Segoe UI", 12F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.txtContraseña.Location = new System.Drawing.Point(100, 420);
            this.txtContraseña.Name = "txtContraseña";
            this.txtContraseña.PasswordChar = '*';
            this.txtContraseña.Size = new System.Drawing.Size(300, 40);
            this.txtContraseña.TabIndex = 6;
            this.txtContraseña.Anchor = AnchorStyles.None;
            // 
            // btnIniciarSesión
            this.btnIniciarSesión.BackColor = System.Drawing.Color.FromArgb(32, 34, 36);
            this.btnIniciarSesión.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnIniciarSesión.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnIniciarSesión.ForeColor = System.Drawing.Color.White;
            this.btnIniciarSesión.Location = new System.Drawing.Point(100, 470);
            this.btnIniciarSesión.Name = "btnIniciarSesión";
            this.btnIniciarSesión.Size = new System.Drawing.Size(300, 60);
            this.btnIniciarSesión.TabIndex = 7;
            this.btnIniciarSesión.Text = "Iniciar Sesión";
            this.btnIniciarSesión.UseVisualStyleBackColor = false;
            this.btnIniciarSesión.Click += new System.EventHandler(this.BtnIniciarSesión_Click);
            this.btnIniciarSesión.Anchor = AnchorStyles.None;
            // 
            // LoginForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.SystemColors.GradientActiveCaption;
            this.ClientSize = new System.Drawing.Size(1366, 768);
            this.Controls.Add(this.containerPanel);
            this.Controls.Add(this.headerPanel);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = true;
            this.Name = "LoginForm";
            this.StartPosition = FormStartPosition.CenterScreen;
            this.Text = "Inicio de Sesión";
            this.Load += new System.EventHandler(this.LoginForm_Load);
            this.headerPanel.ResumeLayout(false);
            this.headerPanel.PerformLayout();
            this.containerPanel.ResumeLayout(false);
            this.mainPanel.ResumeLayout(false);
            this.layoutPanel.ResumeLayout(false);
            this.layoutPanel.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.avatarLabel)).EndInit();
            this.ResumeLayout(false);
        }
        #endregion

        private System.Windows.Forms.Panel headerPanel;
        private System.Windows.Forms.Label headerLabel;
        private System.Windows.Forms.Panel containerPanel;
        private System.Windows.Forms.Panel mainPanel;
        private System.Windows.Forms.TableLayoutPanel layoutPanel;
        private System.Windows.Forms.Label welcomeLabel;
        private System.Windows.Forms.Label subtextLabel;
        private System.Windows.Forms.PictureBox avatarLabel;
        private System.Windows.Forms.Label userLabel;
        private System.Windows.Forms.TextBox txtUsuario;
        private System.Windows.Forms.Label passwordLabel;
        private System.Windows.Forms.TextBox txtContraseña;
        private System.Windows.Forms.Button btnIniciarSesión;
    }
}