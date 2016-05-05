<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('List Notifications'), ['action' => 'index']) ?></li>
    </ul>
</nav>
<div class="notifications form large-9 medium-8 columns content">
    <?= $this->Form->create($notification) ?>
    <fieldset>
        <legend><?= __('Add Notification') ?></legend>
        <?php
            echo $this->Form->input('title');
            echo $this->Form->input('content');
            echo $this->Form->input('createDate', ['empty' => true]);
            echo $this->Form->input('updateDate', ['empty' => true]);
            echo $this->Form->input('delFlg');
        ?>
    </fieldset>
    <?= $this->Form->button(__('Submit')) ?>
    <?= $this->Form->end() ?>
</div>
