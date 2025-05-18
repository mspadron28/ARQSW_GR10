namespace CONUNI_CLIESC_GR10
{
    partial class ConversionForm
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
            this.headerPanel = new System.Windows.Forms.Panel();
            this.headerLabel = new System.Windows.Forms.Label();
            this.containerPanel = new System.Windows.Forms.Panel();
            this.leftPanel = new System.Windows.Forms.Panel();
            this.leftLayoutPanel = new System.Windows.Forms.TableLayoutPanel();
            this.questionLabel = new System.Windows.Forms.Label();
            this.sulleyLabel = new System.Windows.Forms.PictureBox();
            this.rightPanel = new System.Windows.Forms.Panel();
            this.rightLayoutPanel = new System.Windows.Forms.TableLayoutPanel();
            this.selectorLabel = new System.Windows.Forms.Label();
            this.conversionSelector = new System.Windows.Forms.ComboBox();
            this.inputLabel = new System.Windows.Forms.Label();
            this.inputField = new System.Windows.Forms.TextBox();
            this.ConvertButton = new System.Windows.Forms.Button();
            this.resultTextLabel = new System.Windows.Forms.Label();
            this.resultLabel = new System.Windows.Forms.Label();
            this.btnSalir = new System.Windows.Forms.Button();
            this.headerPanel.SuspendLayout();
            this.containerPanel.SuspendLayout();
            this.leftPanel.SuspendLayout();
            this.leftLayoutPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)(this.sulleyLabel)).BeginInit();
            this.rightPanel.SuspendLayout();
            this.rightLayoutPanel.SuspendLayout();
            this.SuspendLayout();
            // 
            // headerPanel
            // 
            this.headerPanel.BackColor = System.Drawing.Color.FromArgb(70, 83, 93);
            this.headerPanel.Controls.Add(this.headerLabel);
            this.headerPanel.Dock = System.Windows.Forms.DockStyle.Top;
            this.headerPanel.Location = new System.Drawing.Point(0, 0);
            this.headerPanel.Name = "headerPanel";
            this.headerPanel.Size = new System.Drawing.Size(1366, 50);
            this.headerPanel.TabIndex = 0;
            // 
            // headerLabel
            // 
            this.headerLabel.AutoSize = true;
            this.headerLabel.Font = new System.Drawing.Font("Segoe UI", 18F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.headerLabel.ForeColor = System.Drawing.Color.White;
            this.headerLabel.Location = new System.Drawing.Point(20, 10);
            this.headerLabel.Name = "headerLabel";
            this.headerLabel.Size = new System.Drawing.Size(250, 32);
            this.headerLabel.TabIndex = 0;
            this.headerLabel.Text = "Conversor de Unidades";
            // 
            // containerPanel
            // 
            this.containerPanel.Controls.Add(this.leftPanel);
            this.containerPanel.Controls.Add(this.rightPanel);
            this.containerPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.containerPanel.Location = new System.Drawing.Point(0, 50);
            this.containerPanel.Name = "containerPanel";
            this.containerPanel.Padding = new System.Windows.Forms.Padding(20);
            this.containerPanel.Size = new System.Drawing.Size(1366, 718);
            this.containerPanel.TabIndex = 1;
            // 
            // leftPanel
            // 
            this.leftPanel.BackColor = System.Drawing.Color.FromArgb(246, 247, 246);
            this.leftPanel.Controls.Add(this.leftLayoutPanel);
            this.leftPanel.Location = new System.Drawing.Point(20, 20);
            this.leftPanel.Name = "leftPanel";
            this.leftPanel.Size = new System.Drawing.Size(673, 678);
            this.leftPanel.TabIndex = 0;
            // 
            // leftLayoutPanel
            // 
            this.leftLayoutPanel.ColumnCount = 1;
            this.leftLayoutPanel.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.leftLayoutPanel.Controls.Add(this.questionLabel, 0, 0);
            this.leftLayoutPanel.Controls.Add(this.sulleyLabel, 0, 1);
            this.leftLayoutPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.leftLayoutPanel.Location = new System.Drawing.Point(0, 0);
            this.leftLayoutPanel.Name = "leftLayoutPanel";
            this.leftLayoutPanel.RowCount = 2;
            this.leftLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 80F));
            this.leftLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 400F));
            this.leftLayoutPanel.Size = new System.Drawing.Size(673, 678);
            this.leftLayoutPanel.TabIndex = 0;
            // 
            // questionLabel
            // 
            this.questionLabel.AutoSize = true;
            this.questionLabel.Font = new System.Drawing.Font("Segoe UI", 24F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.questionLabel.Location = new System.Drawing.Point(0, 20);
            this.questionLabel.Name = "questionLabel";
            this.questionLabel.Size = new System.Drawing.Size(673, 45);
            this.questionLabel.TabIndex = 0;
            this.questionLabel.Text = "¿Qué unidad requieres convertir?";
            this.questionLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.questionLabel.Anchor = AnchorStyles.None;
            // 
            // sulleyLabel
            // 
            this.sulleyLabel.Location = new System.Drawing.Point(136, 80);
            this.sulleyLabel.Name = "sulleyLabel";
            this.sulleyLabel.Size = new System.Drawing.Size(400, 400);
            this.sulleyLabel.SizeMode = System.Windows.Forms.PictureBoxSizeMode.Zoom;
            this.sulleyLabel.TabIndex = 1;
            this.sulleyLabel.TabStop = false;
            this.sulleyLabel.Image = System.Drawing.Image.FromFile(Path.Combine(Application.StartupPath, "Images", "sulleyconuni.png"));
            this.sulleyLabel.Anchor = AnchorStyles.None;
            // 
            // rightPanel
            // 
            this.rightPanel.BackColor = System.Drawing.Color.FromArgb(177, 197, 199);
            this.rightPanel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.rightPanel.Controls.Add(this.rightLayoutPanel);
            this.rightPanel.Location = new System.Drawing.Point(698, 139); // Centrado verticalmente
            this.rightPanel.Name = "rightPanel";
            this.rightPanel.Size = new System.Drawing.Size(300, 400);
            this.rightPanel.TabIndex = 1;
            this.rightPanel.Anchor = AnchorStyles.None;
            // 
            // rightLayoutPanel
            // 
            this.rightLayoutPanel.ColumnCount = 1;
            this.rightLayoutPanel.ColumnStyles.Add(new System.Windows.Forms.ColumnStyle(System.Windows.Forms.SizeType.Percent, 100F));
            this.rightLayoutPanel.Controls.Add(this.selectorLabel, 0, 0);
            this.rightLayoutPanel.Controls.Add(this.conversionSelector, 0, 1);
            this.rightLayoutPanel.Controls.Add(this.inputLabel, 0, 2);
            this.rightLayoutPanel.Controls.Add(this.inputField, 0, 3);
            this.rightLayoutPanel.Controls.Add(this.ConvertButton, 0, 4);
            this.rightLayoutPanel.Controls.Add(this.resultTextLabel, 0, 5);
            this.rightLayoutPanel.Controls.Add(this.resultLabel, 0, 6);
            this.rightLayoutPanel.Controls.Add(this.btnSalir, 0, 7);
            this.rightLayoutPanel.Dock = System.Windows.Forms.DockStyle.Fill;
            this.rightLayoutPanel.Location = new System.Drawing.Point(0, 0);
            this.rightLayoutPanel.Name = "rightLayoutPanel";
            this.rightLayoutPanel.RowCount = 8;
            this.rightLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 40F));
            this.rightLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 40F));
            this.rightLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 40F));
            this.rightLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 40F));
            this.rightLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 50F));
            this.rightLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 40F));
            this.rightLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 40F));
            this.rightLayoutPanel.RowStyles.Add(new System.Windows.Forms.RowStyle(System.Windows.Forms.SizeType.Absolute, 50F));
            this.rightLayoutPanel.Size = new System.Drawing.Size(298, 398);
            this.rightLayoutPanel.TabIndex = 0;
            // 
            // selectorLabel
            // 
            this.selectorLabel.AutoSize = true;
            this.selectorLabel.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.selectorLabel.Location = new System.Drawing.Point(0, 10);
            this.selectorLabel.Name = "selectorLabel";
            this.selectorLabel.Size = new System.Drawing.Size(298, 25);
            this.selectorLabel.TabIndex = 0;
            this.selectorLabel.Text = "Selecciona el tipo de cambio";
            this.selectorLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.selectorLabel.Anchor = AnchorStyles.None;
            // 
            // conversionSelector
            // 
            this.conversionSelector.DropDownStyle = System.Windows.Forms.ComboBoxStyle.DropDownList;
            this.conversionSelector.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.conversionSelector.Location = new System.Drawing.Point(49, 40);
            this.conversionSelector.Name = "conversionSelector";
            this.conversionSelector.Size = new System.Drawing.Size(200, 40);
            this.conversionSelector.TabIndex = 1;
            this.conversionSelector.Anchor = AnchorStyles.None;
            // 
            // inputLabel
            // 
            this.inputLabel.AutoSize = true;
            this.inputLabel.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.inputLabel.Location = new System.Drawing.Point(0, 80);
            this.inputLabel.Name = "inputLabel";
            this.inputLabel.Size = new System.Drawing.Size(298, 25);
            this.inputLabel.TabIndex = 2;
            this.inputLabel.Text = "Ingresa el valor";
            this.inputLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.inputLabel.Anchor = AnchorStyles.None;
            // 
            // inputField
            // 
            this.inputField.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.inputField.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.inputField.Location = new System.Drawing.Point(49, 120);
            this.inputField.Name = "inputField";
            this.inputField.Size = new System.Drawing.Size(200, 40);
            this.inputField.TabIndex = 3;
            this.inputField.Anchor = AnchorStyles.None;
            // 
            // ConvertButton
            // 
            this.ConvertButton.BackColor = System.Drawing.Color.FromArgb(32, 34, 36);
            this.ConvertButton.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.ConvertButton.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.ConvertButton.ForeColor = System.Drawing.Color.White;
            this.ConvertButton.Location = new System.Drawing.Point(49, 160);
            this.ConvertButton.Name = "ConvertButton";
            this.ConvertButton.Size = new System.Drawing.Size(200, 50);
            this.ConvertButton.TabIndex = 4;
            this.ConvertButton.Text = "Convertir";
            this.ConvertButton.UseVisualStyleBackColor = false;
            this.ConvertButton.Click += new System.EventHandler(this.ConvertButton_Click);
            this.ConvertButton.Anchor = AnchorStyles.None;
            // 
            // resultTextLabel
            // 
            this.resultTextLabel.AutoSize = true;
            this.resultTextLabel.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.resultTextLabel.Location = new System.Drawing.Point(0, 210);
            this.resultTextLabel.Name = "resultTextLabel";
            this.resultTextLabel.Size = new System.Drawing.Size(298, 25);
            this.resultTextLabel.TabIndex = 5;
            this.resultTextLabel.Text = "Resultado";
            this.resultTextLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.resultTextLabel.Anchor = AnchorStyles.None;
            // 
            // resultLabel
            // 
            this.resultLabel.AutoSize = true;
            this.resultLabel.BackColor = System.Drawing.Color.White;
            this.resultLabel.BorderStyle = System.Windows.Forms.BorderStyle.FixedSingle;
            this.resultLabel.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Regular, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.resultLabel.Location = new System.Drawing.Point(49, 250);
            this.resultLabel.Name = "resultLabel";
            this.resultLabel.Size = new System.Drawing.Size(200, 30);
            this.resultLabel.TabIndex = 6;
            this.resultLabel.Text = "Resultado aparecerá aquí";
            this.resultLabel.TextAlign = System.Drawing.ContentAlignment.MiddleCenter;
            this.resultLabel.Anchor = AnchorStyles.None;
            // 
            // btnSalir
            // 
            this.btnSalir.BackColor = System.Drawing.Color.FromArgb(100, 100, 100);
            this.btnSalir.FlatStyle = System.Windows.Forms.FlatStyle.Flat;
            this.btnSalir.Font = new System.Drawing.Font("Segoe UI", 14F, System.Drawing.FontStyle.Bold, System.Drawing.GraphicsUnit.Point, ((byte)(0)));
            this.btnSalir.ForeColor = System.Drawing.Color.White;
            this.btnSalir.Location = new System.Drawing.Point(49, 300);
            this.btnSalir.Name = "btnSalir";
            this.btnSalir.Size = new System.Drawing.Size(200, 50);
            this.btnSalir.TabIndex = 7;
            this.btnSalir.Text = "Salir";
            this.btnSalir.UseVisualStyleBackColor = false;
            this.btnSalir.Click += new System.EventHandler(this.BtnSalir_Click);
            this.btnSalir.Anchor = AnchorStyles.None;
            // 
            // ConversionForm
            // 
            this.AutoScaleDimensions = new System.Drawing.SizeF(6F, 13F);
            this.AutoScaleMode = System.Windows.Forms.AutoScaleMode.Font;
            this.BackColor = System.Drawing.Color.FromArgb(246, 247, 246);
            this.ClientSize = new System.Drawing.Size(1366, 768);
            this.Controls.Add(this.containerPanel);
            this.Controls.Add(this.headerPanel);
            this.FormBorderStyle = System.Windows.Forms.FormBorderStyle.FixedSingle;
            this.MaximizeBox = true;
            this.Name = "ConversionForm";
            this.StartPosition = System.Windows.Forms.FormStartPosition.CenterScreen;
            this.Text = "Conversor de Unidades";
            this.Load += new System.EventHandler(this.ConversionForm_Load);
            this.headerPanel.ResumeLayout(false);
            this.headerPanel.PerformLayout();
            this.containerPanel.ResumeLayout(false);
            this.leftPanel.ResumeLayout(false);
            this.leftLayoutPanel.ResumeLayout(false);
            this.leftLayoutPanel.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)(this.sulleyLabel)).EndInit();
            this.rightPanel.ResumeLayout(false);
            this.rightLayoutPanel.ResumeLayout(false);
            this.rightLayoutPanel.PerformLayout();
            this.ResumeLayout(false);
        }

        #endregion

        private System.Windows.Forms.Panel headerPanel;
        private System.Windows.Forms.Label headerLabel;
        private System.Windows.Forms.Panel containerPanel;
        private System.Windows.Forms.Panel leftPanel;
        private System.Windows.Forms.TableLayoutPanel leftLayoutPanel;
        private System.Windows.Forms.Label questionLabel;
        private System.Windows.Forms.PictureBox sulleyLabel;
        private System.Windows.Forms.Panel rightPanel;
        private System.Windows.Forms.TableLayoutPanel rightLayoutPanel;
        private System.Windows.Forms.Label selectorLabel;
        private System.Windows.Forms.ComboBox conversionSelector;
        private System.Windows.Forms.Label inputLabel;
        private System.Windows.Forms.TextBox inputField;
        private System.Windows.Forms.Button ConvertButton;
        private System.Windows.Forms.Label resultTextLabel;
        private System.Windows.Forms.Label resultLabel;
        private System.Windows.Forms.Button btnSalir;
    }
}