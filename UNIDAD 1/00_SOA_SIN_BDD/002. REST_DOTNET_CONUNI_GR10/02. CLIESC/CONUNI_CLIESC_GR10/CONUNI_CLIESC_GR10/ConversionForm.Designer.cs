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
            headerPanel = new Panel();
            headerLabel = new Label();
            containerPanel = new Panel();
            leftPanel = new Panel();
            leftLayoutPanel = new TableLayoutPanel();
            questionLabel = new Label();
            sulleyLabel = new PictureBox();
            rightPanel = new Panel();
            rightLayoutPanel = new TableLayoutPanel();
            selectorLabel = new Label();
            conversionSelector = new ComboBox();
            inputLabel = new Label();
            inputField = new TextBox();
            ConvertButton = new Button();
            resultTextLabel = new Label();
            resultLabel = new Label();
            btnSalir = new Button();
            headerPanel.SuspendLayout();
            containerPanel.SuspendLayout();
            leftPanel.SuspendLayout();
            leftLayoutPanel.SuspendLayout();
            ((System.ComponentModel.ISupportInitialize)sulleyLabel).BeginInit();
            rightPanel.SuspendLayout();
            rightLayoutPanel.SuspendLayout();
            SuspendLayout();
            // 
            // headerPanel
            // 
            headerPanel.BackColor = Color.FromArgb(70, 83, 93);
            headerPanel.Controls.Add(headerLabel);
            headerPanel.Dock = DockStyle.Top;
            headerPanel.Location = new Point(0, 0);
            headerPanel.Margin = new Padding(4, 4, 4, 4);
            headerPanel.Name = "headerPanel";
            headerPanel.Size = new Size(1404, 58);
            headerPanel.TabIndex = 0;
            // 
            // headerLabel
            // 
            headerLabel.AutoSize = true;
            headerLabel.Font = new Font("Segoe UI", 18F, FontStyle.Bold, GraphicsUnit.Point, 0);
            headerLabel.ForeColor = Color.White;
            headerLabel.Location = new Point(24, 11);
            headerLabel.Margin = new Padding(4, 0, 4, 0);
            headerLabel.Name = "headerLabel";
            headerLabel.Size = new Size(279, 32);
            headerLabel.TabIndex = 0;
            headerLabel.Text = "Conversor de Unidades";
            // 
            // containerPanel
            // 
            containerPanel.Controls.Add(leftPanel);
            containerPanel.Controls.Add(rightPanel);
            containerPanel.Dock = DockStyle.Fill;
            containerPanel.Location = new Point(0, 58);
            containerPanel.Margin = new Padding(4, 4, 4, 4);
            containerPanel.Name = "containerPanel";
            containerPanel.Padding = new Padding(24, 23, 24, 23);
            containerPanel.Size = new Size(1404, 603);
            containerPanel.TabIndex = 1;
            containerPanel.Paint += containerPanel_Paint;
            // 
            // leftPanel
            // 
            leftPanel.BackColor = Color.FromArgb(246, 247, 246);
            leftPanel.Controls.Add(leftLayoutPanel);
            leftPanel.Location = new Point(24, 23);
            leftPanel.Margin = new Padding(4, 4, 4, 4);
            leftPanel.Name = "leftPanel";
            leftPanel.Size = new Size(785, 782);
            leftPanel.TabIndex = 0;
            // 
            // leftLayoutPanel
            // 
            leftLayoutPanel.ColumnCount = 1;
            leftLayoutPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100F));
            leftLayoutPanel.Controls.Add(questionLabel, 0, 0);
            leftLayoutPanel.Controls.Add(sulleyLabel, 0, 1);
            leftLayoutPanel.Dock = DockStyle.Fill;
            leftLayoutPanel.Location = new Point(0, 0);
            leftLayoutPanel.Margin = new Padding(4, 4, 4, 4);
            leftLayoutPanel.Name = "leftLayoutPanel";
            leftLayoutPanel.RowCount = 2;
            leftLayoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 92F));
            leftLayoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 461F));
            leftLayoutPanel.Size = new Size(785, 782);
            leftLayoutPanel.TabIndex = 0;
            // 
            // questionLabel
            // 
            questionLabel.Anchor = AnchorStyles.None;
            questionLabel.AutoSize = true;
            questionLabel.Font = new Font("Segoe UI", 24F, FontStyle.Bold, GraphicsUnit.Point, 0);
            questionLabel.Location = new Point(135, 23);
            questionLabel.Margin = new Padding(4, 0, 4, 0);
            questionLabel.Name = "questionLabel";
            questionLabel.Size = new Size(514, 45);
            questionLabel.TabIndex = 0;
            questionLabel.Text = "¿Qué unidad requieres convertir?";
            questionLabel.TextAlign = ContentAlignment.MiddleCenter;
            // 
            // sulleyLabel
            // 
            sulleyLabel.Anchor = AnchorStyles.None;
            sulleyLabel.Image = Properties.Resources.sulleyconuni;
            sulleyLabel.Location = new Point(159, 206);
            sulleyLabel.Margin = new Padding(4, 4, 4, 4);
            sulleyLabel.Name = "sulleyLabel";
            sulleyLabel.Size = new Size(466, 461);
            sulleyLabel.SizeMode = PictureBoxSizeMode.Zoom;
            sulleyLabel.TabIndex = 1;
            sulleyLabel.TabStop = false;
            sulleyLabel.Click += sulleyLabel_Click;
            // 
            // rightPanel
            // 
            rightPanel.Anchor = AnchorStyles.None;
            rightPanel.BackColor = Color.FromArgb(177, 197, 199);
            rightPanel.BorderStyle = BorderStyle.FixedSingle;
            rightPanel.Controls.Add(rightLayoutPanel);
            rightPanel.Location = new Point(721, 47);
            rightPanel.Margin = new Padding(4, 4, 4, 4);
            rightPanel.Name = "rightPanel";
            rightPanel.Size = new Size(349, 461);
            rightPanel.TabIndex = 1;
            // 
            // rightLayoutPanel
            // 
            rightLayoutPanel.ColumnCount = 1;
            rightLayoutPanel.ColumnStyles.Add(new ColumnStyle(SizeType.Percent, 100F));
            rightLayoutPanel.Controls.Add(selectorLabel, 0, 0);
            rightLayoutPanel.Controls.Add(conversionSelector, 0, 1);
            rightLayoutPanel.Controls.Add(inputLabel, 0, 2);
            rightLayoutPanel.Controls.Add(inputField, 0, 3);
            rightLayoutPanel.Controls.Add(ConvertButton, 0, 4);
            rightLayoutPanel.Controls.Add(resultTextLabel, 0, 5);
            rightLayoutPanel.Controls.Add(resultLabel, 0, 6);
            rightLayoutPanel.Controls.Add(btnSalir, 0, 7);
            rightLayoutPanel.Dock = DockStyle.Fill;
            rightLayoutPanel.Location = new Point(0, 0);
            rightLayoutPanel.Margin = new Padding(4, 4, 4, 4);
            rightLayoutPanel.Name = "rightLayoutPanel";
            rightLayoutPanel.RowCount = 8;
            rightLayoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 46F));
            rightLayoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 46F));
            rightLayoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 46F));
            rightLayoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 46F));
            rightLayoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 58F));
            rightLayoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 46F));
            rightLayoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 46F));
            rightLayoutPanel.RowStyles.Add(new RowStyle(SizeType.Absolute, 58F));
            rightLayoutPanel.Size = new Size(347, 459);
            rightLayoutPanel.TabIndex = 0;
            // 
            // selectorLabel
            // 
            selectorLabel.Anchor = AnchorStyles.None;
            selectorLabel.AutoSize = true;
            selectorLabel.Font = new Font("Segoe UI", 14F, FontStyle.Regular, GraphicsUnit.Point, 0);
            selectorLabel.Location = new Point(47, 10);
            selectorLabel.Margin = new Padding(4, 0, 4, 0);
            selectorLabel.Name = "selectorLabel";
            selectorLabel.Size = new Size(253, 25);
            selectorLabel.TabIndex = 0;
            selectorLabel.Text = "Selecciona el tipo de cambio";
            selectorLabel.TextAlign = ContentAlignment.MiddleCenter;
            // 
            // conversionSelector
            // 
            conversionSelector.Anchor = AnchorStyles.None;
            conversionSelector.DropDownStyle = ComboBoxStyle.DropDownList;
            conversionSelector.Font = new Font("Segoe UI", 14F, FontStyle.Regular, GraphicsUnit.Point, 0);
            conversionSelector.Location = new Point(57, 52);
            conversionSelector.Margin = new Padding(4, 4, 4, 4);
            conversionSelector.Name = "conversionSelector";
            conversionSelector.Size = new Size(232, 33);
            conversionSelector.TabIndex = 1;
            conversionSelector.SelectedIndexChanged += conversionSelector_SelectedIndexChanged;
            // 
            // inputLabel
            // 
            inputLabel.Anchor = AnchorStyles.None;
            inputLabel.AutoSize = true;
            inputLabel.Font = new Font("Segoe UI", 14F, FontStyle.Regular, GraphicsUnit.Point, 0);
            inputLabel.Location = new Point(103, 102);
            inputLabel.Margin = new Padding(4, 0, 4, 0);
            inputLabel.Name = "inputLabel";
            inputLabel.Size = new Size(141, 25);
            inputLabel.TabIndex = 2;
            inputLabel.Text = "Ingresa el valor";
            inputLabel.TextAlign = ContentAlignment.MiddleCenter;
            // 
            // inputField
            // 
            inputField.Anchor = AnchorStyles.None;
            inputField.BorderStyle = BorderStyle.FixedSingle;
            inputField.Font = new Font("Segoe UI", 14F, FontStyle.Regular, GraphicsUnit.Point, 0);
            inputField.Location = new Point(57, 145);
            inputField.Margin = new Padding(4, 4, 4, 4);
            inputField.Name = "inputField";
            inputField.Size = new Size(233, 32);
            inputField.TabIndex = 3;
            inputField.TextChanged += inputField_TextChanged;
            // 
            // ConvertButton
            // 
            ConvertButton.Anchor = AnchorStyles.None;
            ConvertButton.BackColor = Color.FromArgb(32, 34, 36);
            ConvertButton.FlatStyle = FlatStyle.Flat;
            ConvertButton.Font = new Font("Segoe UI", 14F, FontStyle.Bold, GraphicsUnit.Point, 0);
            ConvertButton.ForeColor = Color.White;
            ConvertButton.Location = new Point(56, 188);
            ConvertButton.Margin = new Padding(4, 4, 4, 4);
            ConvertButton.Name = "ConvertButton";
            ConvertButton.Size = new Size(234, 50);
            ConvertButton.TabIndex = 4;
            ConvertButton.Text = "Convertir";
            ConvertButton.UseVisualStyleBackColor = false;
            ConvertButton.Click += ConvertButton_Click;
            // 
            // resultTextLabel
            // 
            resultTextLabel.Anchor = AnchorStyles.None;
            resultTextLabel.AutoSize = true;
            resultTextLabel.Font = new Font("Segoe UI", 14F, FontStyle.Regular, GraphicsUnit.Point, 0);
            resultTextLabel.Location = new Point(126, 252);
            resultTextLabel.Margin = new Padding(4, 0, 4, 0);
            resultTextLabel.Name = "resultTextLabel";
            resultTextLabel.Size = new Size(94, 25);
            resultTextLabel.TabIndex = 5;
            resultTextLabel.Text = "Resultado";
            resultTextLabel.TextAlign = ContentAlignment.MiddleCenter;
            // 
            // resultLabel
            // 
            resultLabel.Anchor = AnchorStyles.None;
            resultLabel.AutoSize = true;
            resultLabel.BackColor = Color.White;
            resultLabel.BorderStyle = BorderStyle.FixedSingle;
            resultLabel.Font = new Font("Segoe UI", 14F, FontStyle.Regular, GraphicsUnit.Point, 0);
            resultLabel.Location = new Point(60, 297);
            resultLabel.Margin = new Padding(4, 0, 4, 0);
            resultLabel.Name = "resultLabel";
            resultLabel.Size = new Size(227, 27);
            resultLabel.TabIndex = 6;
            resultLabel.Text = "Resultado aparecerá aquí";
            resultLabel.TextAlign = ContentAlignment.MiddleCenter;
            resultLabel.Click += resultLabel_Click;
            // 
            // btnSalir
            // 
            btnSalir.Anchor = AnchorStyles.None;
            btnSalir.BackColor = Color.FromArgb(100, 100, 100);
            btnSalir.FlatStyle = FlatStyle.Flat;
            btnSalir.Font = new Font("Segoe UI", 14F, FontStyle.Bold, GraphicsUnit.Point, 0);
            btnSalir.ForeColor = Color.White;
            btnSalir.Location = new Point(56, 367);
            btnSalir.Margin = new Padding(4, 4, 4, 4);
            btnSalir.Name = "btnSalir";
            btnSalir.Size = new Size(234, 58);
            btnSalir.TabIndex = 7;
            btnSalir.Text = "Salir";
            btnSalir.UseVisualStyleBackColor = false;
            btnSalir.Click += BtnSalir_Click;
            // 
            // ConversionForm
            // 
            AutoScaleDimensions = new SizeF(7F, 15F);
            AutoScaleMode = AutoScaleMode.Font;
            BackColor = Color.FromArgb(246, 247, 246);
            ClientSize = new Size(1404, 661);
            Controls.Add(containerPanel);
            Controls.Add(headerPanel);
            FormBorderStyle = FormBorderStyle.FixedSingle;
            Margin = new Padding(4, 4, 4, 4);
            Name = "ConversionForm";
            StartPosition = FormStartPosition.CenterScreen;
            Text = "Conversor de Unidades";
            Load += ConversionForm_Load;
            headerPanel.ResumeLayout(false);
            headerPanel.PerformLayout();
            containerPanel.ResumeLayout(false);
            leftPanel.ResumeLayout(false);
            leftLayoutPanel.ResumeLayout(false);
            leftLayoutPanel.PerformLayout();
            ((System.ComponentModel.ISupportInitialize)sulleyLabel).EndInit();
            rightPanel.ResumeLayout(false);
            rightLayoutPanel.ResumeLayout(false);
            rightLayoutPanel.PerformLayout();
            ResumeLayout(false);
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