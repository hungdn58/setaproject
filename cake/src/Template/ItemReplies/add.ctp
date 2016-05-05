<nav class="large-3 medium-4 columns" id="actions-sidebar">
    <ul class="side-nav">
        <li class="heading"><?= __('Actions') ?></li>
        <li><?= $this->Html->link(__('List Item Replies'), ['action' => 'index']) ?></li>
    </ul>
</nav>
<div class="itemReplies form large-9 medium-8 columns content">
    <?= $this->Form->create($itemReply) ?>
    <fieldset>
        <legend><?= __('Add Item Reply') ?></legend>
        <?php
            echo $this->Form->input('comments');
            echo $this->Form->input('writeUserID');
            echo $this->Form->input('itemID');
            echo $this->Form->input('createDate');
        ?>
    </fieldset>
    <?= $this->Form->button(__('Submit')) ?>
    <?= $this->Form->end() ?>
</div>
