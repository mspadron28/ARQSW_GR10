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
            headerPanel = new Panel();
            headerLabel = new Label();
            containerPanel = new Panel();
            mainPanel = new Panel();
            layoutPanel = new TableLayoutPanel();
            welcomeLabel = new Label();
            subtextLabel = new Label();
            avatarLabel = new PictureBox();
            userLabel = new Label();
            txtUsuario = new TextBox();
            passwordLabel = new Label();
            txtContraseña = new TextBox();
            btnIniciarSesión = new Button();
            headerPanel.SuspendLayout();
            containerPanel.SuspendLayout();
            mainPanel.SuspendLayout();
            layoutPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)avatarLabel).BeginInit();
            SuspendLayout();
            // 
            // headerPanel
            // 
            headerPanel.BackColor = Color.FromArgb(70, 83, 93);
            headerPanel.Controls.Add(headerLabel);
            headerPanel.Dock = DockStyle.Top;
            headerPanel.Location = new Point(0, 0);
            headerPanel.Margin = new Padding(4, 5, 4, 5);
            headerPanel.Name = "headerPanel";
            headerPanel.Size = new Size(1821, 92);
            headerPanel.TabIndex = 0;
            // 
            // headerLabel
            // 
            headerLabel.AutoSize = true;
            headerLabel.Font = new Font("Segoe UI", 18F, FontStyle.Bold, GraphicsUnit.Point, 0);
            headerLabel.ForeColor = Color.White;
            headerLabel.Location = new Point(27, 23);
            headerLabel.Margin = new Padding(4, 0, 4, 0);
            headerLabel.Name = "headerLabel";
            headerLabel.Size = new Size(234, 41);
            headerLabel.TabIndex = 0;
            headerLabel.Text = "Inicio de sesión";
            // 
            // containerPanel
            // 
            containerPanel.Controls.Add(mainPanel);
            containerPanel.Dock = DockStyle.Fill;
            containerPanel.Location = new Point(0, 92);
            containerPanel.Margin = new Padding(4, 5, 4, 5);
            containerPanel.Name = "containerPanel";
            containerPanel.Padding = new Padding(27, 31, 27, 31);
            containerPanel.Size = new Size(1821, 963);
            containerPanel.TabIndex = 1;
            // 
            // mainPanel
            // 
            mainPanel.Anchor = AnchorStyles.None;
            mainPanel.BackColor = Color.FromArgb(246, 247, 246);
            mainPanel.Controls.Add(layoutPanel);
            mainPanel.Location = new Point(577, 20);
            mainPanel.Margin = new Padding(4, 5, 4, 5);
            mainPanel.Name = "mainPanel";
            mainPanel.Size = new Size(667, 923);
            mainPanel.TabIndex = 0;
            // 
            // layoutPanel
            // 
            layoutPanel.ColumnCount = 1;
            layoutPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100F));
            layoutPanel.Controls.Add(welcomeLabel, 0, 0);
            layoutPanel.Controls.Add(subtextLabel, 0, 1);
            layoutPanel.Controls.Add(avatarLabel, 0, 2);
            layoutPanel.Controls.Add(userLabel, 0, 3);
            layoutPanel.Controls.Add(txtUsuario, 0, 4);
            layoutPanel.Controls.Add(passwordLabel, 0, 5);
            layoutPanel.Controls.Add(txtContraseña, 0, 6);
            layoutPanel.Controls.Add(btnIniciarSesión, 0, 7);
            layoutPanel.Dock = DockStyle.Fill;
            layoutPanel.Location = new Point(0, 0);
            layoutPanel.Margin = new Padding(4, 5, 4, 5);
            layoutPanel.Name = "layoutPanel";
            layoutPanel.RowCount = 8;
            layoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 123F));
            layoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 77F));
            layoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 277F));
            layoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 77F));
            layoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 77F));
            layoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 77F));
            layoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 77F));
            layoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 123F));
            layoutPanel.Size = new Size(667, 923);
            layoutPanel.TabIndex = 0;
            // 
            // welcomeLabel
            // 
            welcomeLabel.Anchor = AnchorStyles.None;
            welcomeLabel.AutoSize = true;
            welcomeLabel.Font = new Font("Segoe UI", 24F, FontStyle.Bold, GraphicsUnit.Point, 0);
            welcomeLabel.Location = new Point(216, 34);
            welcomeLabel.Margin = new Padding(4, 0, 4, 0);
            welcomeLabel.Name = "welcomeLabel";
            welcomeLabel.Size = new Size(234, 54);
            welcomeLabel.TabIndex = 0;
            welcomeLabel.Text = "Bienvenido";
            welcomeLabel.TextAlign = ContentAlignment.MiddleCenter;
            // 
            // subtextLabel
            // 
            subtextLabel.Anchor = AnchorStyles.None;
            subtextLabel.AutoSize = true;
            subtextLabel.Font = new Font("Segoe UI", 14F, FontStyle.Regular, GraphicsUnit.Point, 0);
            subtextLabel.ForeColor = Color.FromArgb(102, 102, 102);
            subtextLabel.Location = new Point(146, 145);
            subtextLabel.Margin = new Padding(4, 0, 4, 0);
            subtextLabel.Name = "subtextLabel";
            subtextLabel.Size = new Size(375, 32);
            subtextLabel.TabIndex = 1;
            subtextLabel.Text = "Por favor, ingresa tus credenciales";
            subtextLabel.TextAlign = ContentAlignment.MiddleCenter;
            // 
            // avatarLabel
            // 
            avatarLabel.AccessibleRole = AccessibleRole.None;
            avatarLabel.Anchor = AnchorStyles.None;
            avatarLabel.Image = Properties.Resources.sulley;
            avatarLabel.Location = new Point(233, 223);
            avatarLabel.Margin = new Padding(4, 5, 4, 5);
            avatarLabel.Name = "avatarLabel";
            avatarLabel.Size = new Size(200, 231);
            avatarLabel.SizeMode = PictureBoxSizeMode.Zoom;
            avatarLabel.TabIndex = 2;
            avatarLabel.TabStop = false;
            // 
            // userLabel
            // 
            userLabel.Anchor = AnchorStyles.None;
            userLabel.AutoSize = true;
            userLabel.Font = new Font("Segoe UI", 14F, FontStyle.Regular, GraphicsUnit.Point, 0);
            userLabel.Location = new Point(286, 499);
            userLabel.Margin = new Padding(4, 0, 4, 0);
            userLabel.Name = "userLabel";
            userLabel.Size = new Size(94, 32);
            userLabel.TabIndex = 3;
            userLabel.Text = "Usuario";
            // 
            // txtUsuario
            // 
            txtUsuario.Anchor = AnchorStyles.None;
            txtUsuario.BorderStyle = BorderStyle.FixedSingle;
            txtUsuario.Font = new Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point, 0);
            txtUsuario.Location = new Point(134, 575);
            txtUsuario.Margin = new Padding(4, 5, 4, 5);
            txtUsuario.Name = "txtUsuario";
            txtUsuario.Size = new Size(399, 34);
            txtUsuario.TabIndex = 4;
            txtUsuario.TextChanged += txtUsuario_TextChanged;
            // 
            // passwordLabel
            // 
            passwordLabel.Anchor = AnchorStyles.None;
            passwordLabel.AutoSize = true;
            passwordLabel.Font = new Font("Segoe UI", 14F, FontStyle.Regular, GraphicsUnit.Point, 0);
            passwordLabel.Location = new Point(266, 653);
            passwordLabel.Margin = new Padding(4, 0, 4, 0);
            passwordLabel.Name = "passwordLabel";
            passwordLabel.Size = new Size(134, 32);
            passwordLabel.TabIndex = 5;
            passwordLabel.Text = "Contraseña";
            // 
            // txtContraseña
            // 
            txtContraseña.Anchor = AnchorStyles.None;
            txtContraseña.BorderStyle = BorderStyle.FixedSingle;
            txtContraseña.Font = new Font("Segoe UI", 12F, FontStyle.Regular, GraphicsUnit.Point, 0);
            txtContraseña.Location = new Point(134, 729);
            txtContraseña.Margin = new Padding(4, 5, 4, 5);
            txtContraseña.Name = "txtContraseña";
            txtContraseña.PasswordChar = '*';
            txtContraseña.Size = new Size(399, 34);
            txtContraseña.TabIndex = 6;
            // 
            // btnIniciarSesión
            // 
            btnIniciarSesión.Anchor = AnchorStyles.None;
            btnIniciarSesión.BackColor = Color.FromArgb(32, 34, 36);
            btnIniciarSesión.FlatStyle = FlatStyle.Flat;
            btnIniciarSesión.Font = new Font("Segoe UI", 14F, FontStyle.Bold, GraphicsUnit.Point, 0);
            btnIniciarSesión.ForeColor = Color.White;
            btnIniciarSesión.Location = new Point(133, 808);
            btnIniciarSesión.Margin = new Padding(4, 5, 4, 5);
            btnIniciarSesión.Name = "btnIniciarSesión";
            btnIniciarSesión.Size = new Size(400, 92);
            btnIniciarSesión.TabIndex = 7;
            btnIniciarSesión.Text = "Iniciar Sesión";
            btnIniciarSesión.UseVisualStyleBackColor = false;
            btnIniciarSesión.Click += BtnIniciarSesión_Click;
            // 
            // LoginForm
            // 
            AutoScaleDimensions = new SizeF(8F, 20F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = SystemColors.GradientActiveCaption;
            ClientSize = new Size(1821, 1055);
            Controls.Add(containerPanel);
            Controls.Add(headerPanel);
            FormBorderStyle = FormBorderStyle.FixedSingle;
            Margin = new Padding(4, 5, 4, 5);
            Name = "LoginForm";
            StartPosition = FormStartPosition.CenterScreen;
            Text = "Inicio de Sesión";
            Load += LoginForm_Load;
            headerPanel.ResumeLayout(false);
            headerPanel.PerformLayout();
            containerPanel.ResumeLayout(false);
            mainPanel.ResumeLayout(false);
            layoutPanel.ResumeLayout(false);
            layoutPanel.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)avatarLabel).EndInit();
            ResumeLayout(false);
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