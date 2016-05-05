<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('List Users'), ['action' => 'index']) ?></li>
    </ul>
</nav>
<div class="users form large-9 medium-8 columns content">
    <?= $this->Form->create($user) ?>
    <fieldset>
        <legend><?= __('Add User') ?></legend>
        <?php
            echo $this->Form->input('userId');
            echo $this->Form->input('nickname');
            echo $this->Form->input('address');
            echo $this->Form->input('profileImage');
            echo $this->Form->input('description');
            echo $this->Form->input('gender');
            echo $this->Form->input('birthday', ['empty' => true]);
            echo $this->Form->input('works');
            echo $this->Form->input('lat');
            echo $this->Form->input('long');
            echo $this->Form->input('createDate');
            echo $this->Form->input('updateDate');
            echo $this->Form->input('delFlg');
        ?>
    </fieldset>
    <?= $this->Form->button(__('Submit')) ?>
    <?= $this->Form->end() ?>
</div>
